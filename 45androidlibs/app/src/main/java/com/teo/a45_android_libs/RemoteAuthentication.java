package com.teo.a45_android_libs;

import com.google.firebase.auth.FirebaseAuth;

public class RemoteAuthentication {

    public static RemoteAuthentication auth = new RemoteAuthentication();
    private final FirebaseAuth firebaseAuth;

    private RemoteAuthentication() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void register(String email, String password, DataLister<String> callback) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        callback.onSuccess(task.getResult().getUser().getUid());
                    }
                })
                .addOnFailureListener(e -> {

                });
    }

    public interface DataLister<T> {
        void onSuccess(T data);
        void onFailure();
    }
}
