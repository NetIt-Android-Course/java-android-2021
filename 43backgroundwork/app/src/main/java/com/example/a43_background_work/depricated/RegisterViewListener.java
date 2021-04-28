package com.example.a43_background_work.depricated;

import java.util.List;

public interface RegisterViewListener {
    void showImageLinks(String[] data);
    void showBreeds(List<String> data);
    void showError(String error);
    void showMessage(String message);
    void navigateToLoginScreen();
}