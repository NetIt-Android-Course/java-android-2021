package com.example.a43_background_work.presenters.contracts.register;

import com.example.a43_background_work.ui.models.RegisterViewModel;

public interface RegisterPresenterListener {

    void setCallback(RegisterViewListener callback);
    void onUiLoaded();
    void onRegisterClicked(RegisterViewModel model);
    void onBreedSelected(String selectedBreed);
}
