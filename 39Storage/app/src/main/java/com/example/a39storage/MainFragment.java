package com.example.a39storage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

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

        view.findViewById(R.id.btnSettings).setOnClickListener(view1 -> onSettingsClicked(view));
        view.findViewById(R.id.btnLocation).setOnClickListener(view1 -> onLocationClicked());
        txtLocation = view.findViewById(R.id.txtLocation);
    }

    @SuppressLint("MissingPermission")
    private void onLocationClicked() {
        if(hasPermission()) {
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
            requestPermission();
        }
    }

    private void requestPermission() {
        requestPermissions(
                new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, 123);
    }

    private boolean hasPermission() {
        return ContextCompat.checkSelfPermission(
                getContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED;
    }

    private void onSettingsClicked(View view) {
        Navigation.findNavController(view).navigate(R.id.action_fragmentMain_to_settingsFragment);
    }
}
