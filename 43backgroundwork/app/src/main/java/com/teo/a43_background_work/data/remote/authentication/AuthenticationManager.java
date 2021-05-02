package com.teo.a43_background_work.data.remote.authentication;

import androidx.annotation.NonNull;

import com.teo.a43_background_work.data.repositories.AuthRepository;
import com.teo.a43_background_work.data.repositories.UserRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthenticationManager implements AuthRepository {

    private final FirebaseAuth auth;
    private final UserRepository userRepository;

    public AuthenticationManager(UserRepository userRepository) {
        this.userRepository = userRepository;
        auth = FirebaseAuth.getInstance();
    }

    public boolean hasLoggedUser() {
        return auth.getCurrentUser() != null;
    }

    public void logout() {
        auth.signOut();
    }

    public String getLoggerUserId() {
        return auth.getUid();
    }

    @Override
    public void register(String name, int age, String email, String password, AuthRepository.AuthListener callback) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    userRepository.insert(name, age, email);
                    callback.onSuccess();
                }
                else callback.onFailure("Registration unsuccesful.");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e.getMessage());
            }
        });
    }

    public void login(String email, String password, AuthRepository.AuthListener callback) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) callback.onSuccess();
                else callback.onFailure("Registration unsuccesful.");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e.getMessage());
            }
        });
    }
}
