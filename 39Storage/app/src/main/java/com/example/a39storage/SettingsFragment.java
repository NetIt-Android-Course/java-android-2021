package com.example.a39storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;


public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SettingsRepository settingsRepository = SettingsRepository.getInstance(getContext());
        SwitchMaterial switchMaterial = view.findViewById(R.id.switchSplash);
        boolean shouldSplashShow = settingsRepository.getSplashEnabled();
        switchMaterial.setChecked(shouldSplashShow);

        switchMaterial.setOnCheckedChangeListener((compoundButton, b) -> {
            Toast.makeText(getContext(), "Switch is switched:" + b, Toast.LENGTH_SHORT).show();

            settingsRepository.setSplashEnabled(b);
        });
    }
}