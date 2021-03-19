package com.teo.a45_android_libs;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class RemoteDatabase {

    private static final String TAG = "RemoteDatabase";
    public static RemoteDatabase db = new RemoteDatabase();
    private final FirebaseFirestore database;

    private RemoteDatabase() {
        database = FirebaseFirestore.getInstance();
    }

    public void doesUserExist(String name, DataListener<Boolean> callback) {
        database.collection("users")
                .whereEqualTo("name", name)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(!task.getResult().isEmpty());
                    } else {
                        callback.onFailure(task.getException().getMessage());
                    }
                });
    }

    public void addUser(String id, String name, String avatarUrl, DataListener<String> callback) {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("avatarUrl", avatarUrl);

        // Add a new document with a generated ID
        database.collection("users")
                .document(id)
                .set(user)
                .addOnSuccessListener(documentReference -> callback.onSuccess(id))
                .addOnFailureListener(e -> callback.onFailure(e.getMessage()));
    }

    public interface DataListener<T> {
        void onSuccess(T data);
        void onFailure(String error);
    }
}
