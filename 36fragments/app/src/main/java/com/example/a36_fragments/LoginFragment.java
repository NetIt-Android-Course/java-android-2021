package com.example.a36_fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {

    private EditText edtEmail;
    private EditText edtPassword;
    private LoginFragmentCallback callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof LoginFragmentCallback) {
            callback = (LoginFragmentCallback) context;
        } else {
            throw new IllegalArgumentException("Implement the interface FIRST!!");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btnLogin).setOnClickListener(view1 -> onLoginClicked());
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
    }

    private void onLoginClicked() {
        String email = edtEmail.getText().toString();
        String pass = edtPassword.getText().toString();

//        ((MainActivity) getActivity()).notifyLoginBtnClicked(email, pass);
        callback.notifyLoginBtnClicked(email, pass);
    }

    private void blabla() {

    }

    interface LoginFragmentCallback {
        void notifyLoginBtnClicked(String email, String pass);

        void notifyForgotPasswordClicked();
    }
}
