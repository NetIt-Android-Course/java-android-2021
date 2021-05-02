package com.teo.a43_background_work.data.repositories;

public interface AuthRepository {

    void register(String name, int age, String email, String password, AuthListener callback);

    interface AuthListener {
        void onSuccess();
        void onFailure(String error);
    }
}
