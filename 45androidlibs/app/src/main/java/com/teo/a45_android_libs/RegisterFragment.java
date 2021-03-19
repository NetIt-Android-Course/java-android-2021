package com.teo.a45_android_libs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teo.a45_android_libs.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private static final String TAG = "RegisterFragment";
    private FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnRegister.setOnClickListener(view1 -> onRegisterClicked());
    }

    private void onRegisterClicked() {
        RemoteAuthentication.auth.register(
                binding.edtEmail.getText().toString(),
                binding.edtPassword.getText().toString(),
                new RemoteAuthentication.DataLister<String>() {
                    @Override
                    public void onSuccess(String id) {
                        checkUserExistsAndAddUserData(id);
                    }

                    @Override
                    public void onFailure() {

                    }
                }
        );
    }

    private void checkUserExistsAndAddUserData(String id) {
        RemoteDatabase.db.doesUserExist(
                binding.edtName.getText().toString(),
                new RemoteDatabase.DataListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean data) {
                        if(data) {
                            Toast.makeText(getContext(), "User with this name already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            registerUser(id);
                        }
                    }

                    @Override
                    public void onFailure(String error) {
                        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void registerUser(String id) {
        RemoteDatabase.db.addUser(
                id,
                binding.edtName.getText().toString(),
                binding.edtAvatarUrl.getText().toString(),
                new RemoteDatabase.DataListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Toast.makeText(getContext(), "User created. ID:" + data, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String error) {
                        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}