package com.example.a43_background_work.data.repositories;

import com.example.a43_background_work.data.remote.authentication.AuthenticationManager;

public interface AuthRepository {

    void register(String email, String password, AuthListener callback);

    interface AuthListener {
        void onSuccess();
        void onFailure(String error);
    }
}
