package com.example.a43_background_work.features.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.a43_background_work.R;
import com.example.a43_background_work.data.remote.authentication.AuthenticationManager;
import com.example.a43_background_work.data.repositories.AuthRepository;
import com.example.a43_background_work.data.repositories.DogImageRepository;
import com.example.a43_background_work.data.repositories.UserRepository;
import com.example.a43_background_work.features.Destination;
import com.example.a43_background_work.features.Message;

import java.util.List;

import javax.inject.Inject;

public class RegisterViewModel {

    private final MutableLiveData<Destination> destinationLiveData = new MutableLiveData<>();
    private final MutableLiveData<Message> messagesLiveData = new MutableLiveData<>();
    private MutableLiveData<RegisterUiModel> uiModelLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> alertsLiveData = new MutableLiveData<>();
    private final RegisterUiModel uiModel;

    private final AuthRepository authRepository;
    private final DogImageRepository dogImageRepository;
    private final UserRepository userRepository;

    @Inject
    public RegisterViewModel(DogImageRepository apiWrapper, UserRepository asyncDatabase,
                             AuthRepository authenticationManager) {
        this.authRepository = authenticationManager;
        this.dogImageRepository = apiWrapper;
        this.userRepository = asyncDatabase;
        this.uiModel = new RegisterUiModel();
    }

    public void onBreedSelected(String selectedBreed) {
        uiModel.selectedBreed = selectedBreed;
        messagesLiveData.postValue(new Message("Selected breed: " + selectedBreed, false));

        dogImageRepository.getImagesUrlByBreed(selectedBreed, new DogImageRepository.OnApiResultListener<String[]>() {
            @Override
            public void onSuccess(String[] data) {
                uiModel.showImageLinks = data;
                uiModelLiveData.postValue(uiModel);
            }

            @Override
            public void onFailure() {
                messagesLiveData.postValue(new Message("Loading Breeds failed!", true));
            }
        });
    }

    public void onRegisterClicked() {
        alertsLiveData.postValue(true);
    }

    public void onUiLoaded() {
        subscribeToUserList();
        dogImageRepository.getAllBreeds(new DogImageRepository.OnApiResultListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                uiModel.breeds = data;
                uiModelLiveData.postValue(uiModel);
            }

            @Override
            public void onFailure() {
                messagesLiveData.postValue(new Message("Breeds failed!", true));
            }
        });
    }

    private void subscribeToUserList() {
        userRepository.getAllUsers().observeForever(userEntities -> {
            messagesLiveData.postValue(new Message("DB user changed", false));

        });
    }

    public LiveData<RegisterUiModel> getUiModelLiveData() {
        return uiModelLiveData;
    }

    public LiveData<Destination> getDestinationLiveData() {
        return destinationLiveData;
    }

    public LiveData<Message> getMessagesLiveData() {
        return messagesLiveData;
    }

    public MutableLiveData<Boolean> getAlertsLiveData() {
        return alertsLiveData;
    }

    public void onRegisterAlertClicked() {
        authRepository.register(uiModel.email, uiModel.password, new AuthenticationManager.AuthListener() {
            @Override
            public void onSuccess() {
                destinationLiveData.postValue(new Destination(R.id.action_registerFragment_to_loginFragment));
            }

            @Override
            public void onFailure(String error) {
                messagesLiveData.postValue(new Message(error, true));
            }
        });
    }
}
