package com.teo.a43_background_work.presenters;

import com.teo.a43_background_work.data.repositories.AuthRepository;
import com.teo.a43_background_work.data.repositories.DogImageRepository;
import com.teo.a43_background_work.data.repositories.UserRepository;
import com.teo.a43_background_work.presenters.contracts.register.RegisterPresenterListener;
import com.teo.a43_background_work.presenters.contracts.register.RegisterViewListener;
import com.teo.a43_background_work.data.remote.authentication.AuthenticationManager;
import com.teo.a43_background_work.ui.models.RegisterViewModel;

import java.util.List;

import javax.inject.Inject;

public class RegisterPresenter implements RegisterPresenterListener {

    private RegisterViewListener callback;
    private final AuthRepository authRepository;
    private final DogImageRepository dogImageRepository;
    private final UserRepository userRepository;

    @Inject
    public RegisterPresenter(DogImageRepository apiWrapper, UserRepository asyncDatabase,
                             AuthRepository authenticationManager) {
        this.authRepository = authenticationManager;
        this.dogImageRepository = apiWrapper;
        this.userRepository = asyncDatabase;
    }

    @Override
    public void setCallback(RegisterViewListener callback) {
        this.callback = callback;
    }

    @Override
    public void onBreedSelected(String selectedBreed) {
        callback.showMessage("Selected breed: " + selectedBreed);
        dogImageRepository.getImagesUrlByBreed(selectedBreed, new DogImageRepository.OnApiResultListener<String[]>() {
            @Override
            public void onSuccess(String[] data) {
                callback.showImageLinks(data);
            }

            @Override
            public void onFailure() {
                callback.showError("Breeds failed!");
            }
        });
    }

    @Override
    public void onRegisterClicked(RegisterViewModel viewModel) {
        authRepository.register(viewModel.name, Integer.parseInt(viewModel.age), viewModel.email, viewModel.password, new AuthenticationManager.AuthListener() {
            @Override
            public void onSuccess() {
                callback.navigateToLoginScreen();
            }

            @Override
            public void onFailure(String error) {
                callback.showError(error);
            }
        });
    }

    @Override
    public void onUiLoaded() {
        subscribeToUserList();
        dogImageRepository.getAllBreeds(new DogImageRepository.OnApiResultListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                callback.showBreeds(data);
            }

            @Override
            public void onFailure() {
                callback.showError("Breeds failed!");
            }
        });
    }

    private void subscribeToUserList() {
        userRepository.getAllUsers().observeForever(userEntities -> callback.showError("User database changed"));
    }
}
