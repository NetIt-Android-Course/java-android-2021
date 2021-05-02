package com.teo.a43_background_work.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teo.a43_background_work.R;


public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if(AuthenticationManager.getInstance().hasLoggedUser()) {
//            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainFragment);
//        }
        view.findViewById(R.id.txtRegister).setOnClickListener(view1 -> onRegisterClicked(view));
    }

    private void onRegisterClicked(View view) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
    }
}