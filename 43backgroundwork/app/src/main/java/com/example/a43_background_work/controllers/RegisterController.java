package com.example.a43_background_work.controllers;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.a43_background_work.data.local.AsyncDatabase;
import com.example.a43_background_work.data.local.entities.UserEntity;
import com.example.a43_background_work.data.remote.ApiWrapper;
import com.example.a43_background_work.ui.adapters.ImageUrlsAdapter;

import java.util.List;

public class RegisterController {

    private RegisterControllerCallback callback;
    public MutableLiveData<String> showErrorLiveData = new MutableLiveData<>();

    public RegisterController(RegisterControllerCallback callback) {
        this.callback = callback;
    }

    public void onBreedSelected(String selectedBreed) {
        callback.showMessage("Selected breed: " + selectedBreed);
        ApiWrapper.getInstance().getImagesUrlByBreed(selectedBreed, new ApiWrapper.OnApiResultListener<String[]>() {
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

    public void onRegisterClicked(List<String> selectedImages, String userName, int userAge, String userBreed) {
        List<String> selectedUrls = selectedImages;
        UserEntity userEntity = new UserEntity();
        userEntity.name = userName;
        userEntity.age = userAge;
        userEntity.breed = userBreed;

        AsyncDatabase.getInstance().getUserByNameAndAge(userEntity.name, userEntity.age, new AsyncDatabase.DataReceivedListener<UserEntity>() {
            @Override
            public void onDataReceived(UserEntity data) {
                if(data == null) {
                    AsyncDatabase.getInstance().insert(userEntity);
                } else {
//                    callback.showError("This user already exists. Login.");
                    showErrorLiveData.postValue("This user already exists. Login.");
                }
            }
        });
    }

    public void onUiLoaded() {
        subscribeToUserList();
        ApiWrapper.getInstance().getAllBreeds(new ApiWrapper.OnApiResultListener<List<String>>() {
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
        AsyncDatabase.getInstance().getAllUsers().observeForever(new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(List<UserEntity> userEntities) {
                showErrorLiveData.postValue("User database changed");
            }
        });
    }

    public interface RegisterControllerCallback {
        void showImageLinks(String[] data);
        void showBreeds(List<String> data);
//        void showError(String error);
        void showMessage(String message);
    }
}
