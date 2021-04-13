package com.example.a43_background_work.data.remote.authentication;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthenticationManager {

    private final FirebaseAuth auth;
    private static AuthenticationManager instance;

    public static AuthenticationManager getInstance() {
        if(instance == null) instance = new AuthenticationManager();
        return instance;
    }

    private AuthenticationManager() {
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

    public void register(String email, String password, AuthListener callback) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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

    public void login(String email, String password, AuthListener callback) {
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

    public interface AuthListener {
        void onSuccess();
        void onFailure(String error);
    }
}
