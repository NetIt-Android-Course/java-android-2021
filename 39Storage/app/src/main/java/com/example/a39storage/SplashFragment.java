package com.example.a39storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.Timer;
import java.util.TimerTask;

public class SplashFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//            }
//        }, 3000);

        boolean shouldShowSplash = SettingsRepository.getInstance(getContext()).getSplashEnabled();
        long splashDelay = shouldShowSplash ? 3000 : 0;

        new Handler().postDelayed(() -> Navigation.findNavController(view).navigate(R.id.action_splash_to_main), splashDelay);
    }
}
