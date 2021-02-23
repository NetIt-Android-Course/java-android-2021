package com.example.a39storage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.a39storage.database.AppDatabase;
import com.example.a39storage.database.UserEntity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.os.Environment.DIRECTORY_DOCUMENTS;
import static android.os.Environment.DIRECTORY_PICTURES;

public class MainFragment extends Fragment {

    private TextView txtLocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btnSettings).setOnClickListener(view1 -> printAllUsertToLog());
        view.findViewById(R.id.btnLocation).setOnClickListener(view1 -> onLocationClicked());
        view.findViewById(R.id.btnCreateTextFile).setOnClickListener(view1 -> onCreateTextFileClicked());
        view.findViewById(R.id.btnCreateImage).setOnClickListener(view1 -> pickImageFileLocation());
        view.findViewById(R.id.btnAddUser).setOnClickListener(view1 -> onAddUserClicked(view));
        txtLocation = view.findViewById(R.id.txtLocation);
    }

    private void onAddUserClicked(View view) {
        String name = ((EditText) view.findViewById(R.id.editTextTextPersonName)).getText().toString();
        String email = ((EditText) view.findViewById(R.id.editTextTextEmailAddress)).getText().toString();
        addUserToDb(name, email);
    }

    private void printAllUsertToLog() {

//        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail("pesho@abv.bg");
//        userEntity.setName("Pesho");

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // new Thread
                AppDatabase db = AppDatabase.getInstance(getContext());
//                db.userDao().insert(userEntity);
                List<UserEntity> userEntities = db.userDao().getAll();
                for (UserEntity user :
                        userEntities) {
                    Log.e("MainFragment", user.toString());
                }

                return null;
            }
        }.execute();


    }


    private void addUserToDb(String name, String email) {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setName(name);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // new Thread
                AppDatabase db = AppDatabase.getInstance(getContext());
                db.userDao().insert(userEntity);
                return null;
            }
        }.execute();


    }

    private void onCreateTextFileClicked() {
        if(hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            pickTextFileLocation();
        } else {
            requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    public void generateTextNoteOnHardDrive(Context context, Uri fileLocationUri, String sBody) {
        try {
            OutputStream writer = context.getContentResolver().openOutputStream(fileLocationUri);
            writer.write(sBody.getBytes());
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Request code for creating a PDF document.
    private static final int CREATE_TEXT_FILE = 1;
    private static final int CREATE_FISH_FILE = 2;

    private void pickTextFileLocation() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/text");
        intent.putExtra(Intent.EXTRA_TITLE, "invoice.txt");

        // Optionally, specify a URI for the directory that should be opened in
        // the system file picker when your app creates the document.
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, getContext().getExternalFilesDir(DIRECTORY_DOCUMENTS));

        startActivityForResult(intent, CREATE_TEXT_FILE);
    }

    private void pickImageFileLocation() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/image");
        intent.putExtra(Intent.EXTRA_TITLE, "fish.jpg");

        // Optionally, specify a URI for the directory that should be opened in
        // the system file picker when your app creates the document.
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, getContext().getExternalFilesDir(DIRECTORY_PICTURES));

        startActivityForResult(intent, CREATE_FISH_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if(resultCode == RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                createFile(requestCode, uri);
            }
        }
    }

    private void createFile(int requestCode, Uri uri) {
        if(requestCode == CREATE_TEXT_FILE) {
            generateTextNoteOnHardDrive(getContext(), uri, "bla bla pls work");
        } else if(requestCode == CREATE_FISH_FILE) {
            generateImageOnHardDrive(uri, BitmapFactory.decodeResource(getContext().getResources(), R.drawable.fish));
        }
    }

    private void generateImageOnHardDrive(Uri uri, Bitmap image) {
        try {
            OutputStream writer = getContext().getContentResolver().openOutputStream(uri);
            image.compress(Bitmap.CompressFormat.JPEG, 100, writer);
            writer.flush();
            writer.close();
            Toast.makeText(getContext(), "Saved Image", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("MissingPermission")
    private void onLocationClicked() {
        if(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                txtLocation.setText(location.toString());
                            }
                        }
                    });
        } else {
            requestPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    private void requestPermission(String permission) {
        requestPermissions(
                new String[] { permission }, 123);
    }

    private boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(
                getContext(), permission) ==
                PackageManager.PERMISSION_GRANTED;
    }

    private void onSettingsClicked(View view) {
        Navigation.findNavController(view).navigate(R.id.action_fragmentMain_to_settingsFragment);
    }
}
