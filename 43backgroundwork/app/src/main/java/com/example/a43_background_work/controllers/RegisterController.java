package com.example.a43_background_work.controllers;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.a43_background_work.data.local.AsyncDatabase;
import com.example.a43_background_work.data.local.entities.UserEntity;
import com.example.a43_background_work.data.remote.authentication.AuthenticationManager;
import com.example.a43_background_work.data.remote.dog_images.ApiWrapper;
import com.example.a43_background_work.ui.models.RegisterViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.Provides;

public class RegisterController {

    private AuthenticationManager authenticationManager;
    private RegisterControllerCallback callback;
    private ApiWrapper apiWrapper;
    private AsyncDatabase asyncDatabase;
    public MutableLiveData<String> showErrorLiveData = new MutableLiveData<>();

    @Inject
    public RegisterController(ApiWrapper apiWrapper, AsyncDatabase asyncDatabase,
                              AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.apiWrapper = apiWrapper;
        this.asyncDatabase = asyncDatabase;
    }

    public void setCallback(RegisterControllerCallback callback) {
        this.callback = callback;
    }

    public void onBreedSelected(String selectedBreed) {
        callback.showMessage("Selected breed: " + selectedBreed);
        apiWrapper.getImagesUrlByBreed(selectedBreed, new ApiWrapper.OnApiResultListener<String[]>() {
            @Override
            public void onSuccess(String[] data) {
                callback.showImageLinks(data);
            }

            @Override
            public void onFailure() {
//                callback.showError("Breeds failed!");
                showErrorLiveData.postValue("Breeds failed!");
            }
        });
    }

    public void onRegisterClicked(RegisterViewModel viewModel) {
        authenticationManager.register(viewModel.email, viewModel.password, new AuthenticationManager.AuthListener() {
            @Override
            public void onSuccess() {
                callback.navigateToLoginScreen();
            }

            @Override
            public void onFailure(String error) {
                showErrorLiveData.postValue(error);
            }
        });
    }

    public void onUiLoaded() {
        subscribeToUserList();
        apiWrapper.getAllBreeds(new ApiWrapper.OnApiResultListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                callback.showBreeds(data);
            }

            @Override
            public void onFailure() {
//                callback.showError("Breeds failed!");
                showErrorLiveData.postValue("Breeds failed!");
            }
        });
    }

    private void subscribeToUserList() {
        asyncDatabase.getAllUsers().observeForever(userEntities -> showErrorLiveData.postValue("User database changed"));
    }

    public interface RegisterControllerCallback {
        void showImageLinks(String[] data);
        void showBreeds(List<String> data);
//        void showError(String error);
        void showMessage(String message);
        void navigateToLoginScreen();
    }
}
