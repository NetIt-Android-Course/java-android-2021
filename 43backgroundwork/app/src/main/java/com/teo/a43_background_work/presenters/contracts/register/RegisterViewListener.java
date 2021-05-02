package com.teo.a43_background_work.presenters.contracts.register;

import java.util.List;

public interface RegisterViewListener {
    void showImageLinks(String[] data);
    void showBreeds(List<String> data);
    void showError(String error);
    void showMessage(String message);
    void navigateToLoginScreen();
}