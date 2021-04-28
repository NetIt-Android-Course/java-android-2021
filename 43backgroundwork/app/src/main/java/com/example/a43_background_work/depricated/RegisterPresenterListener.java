package com.example.a43_background_work.depricated;

import com.example.a43_background_work.features.register.RegisterUiModel;

public interface RegisterPresenterListener {

    void setCallback(RegisterViewListener callback);
    void onUiLoaded();
    void onRegisterClicked(RegisterUiModel model);
    void onBreedSelected(String selectedBreed);
}
