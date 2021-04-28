package com.example.a43_background_work.features.register;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.a43_background_work.DogTinderApplication;
import com.example.a43_background_work.R;
import com.example.a43_background_work.databinding.FragmentRegisterBinding;
import com.example.a43_background_work.features.Destination;
import com.example.a43_background_work.features.Message;

import javax.inject.Inject;

public class RegisterFragment extends Fragment {

    @Inject
    RegisterViewModel viewModel;
    private FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((DogTinderApplication) getActivity().getApplication()).getAppComponent().inject(this);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSpinnerListener();
        initRegisterListener();
        viewModel.getUiModelLiveData().observe(this.getViewLifecycleOwner(), registerUiModel -> binding.setUiModel(registerUiModel));
        viewModel.getDestinationLiveData().observe(getViewLifecycleOwner(), destination -> navigateTo(destination));
        viewModel.getMessagesLiveData().observe(getViewLifecycleOwner(), message -> showMessage(message));
        viewModel.getAlertsLiveData().observe(getViewLifecycleOwner(), aBoolean -> showAlert());
        viewModel.onUiLoaded();
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        View view = getLayoutInflater().inflate(R.layout.fragment_splash, null);
        AlertDialog alertDialog = builder
                .setCancelable(false)
//                .setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, new String[]{"Pesho", "Ivan"}), null)
//                .setItems(new String[]{"Pesho", "Ivan"}, null)
//                .setMultiChoiceItems(new String[]{"Pesho", "Ivan"}, new boolean[]{true, false}, null)
//                .setView(view)
                .setTitle("Register?")
                .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        viewModel.onRegisterAlertClicked();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        alertDialog.show();
    }

    private void showMessage(Message message) {
        Toast.makeText(getContext(), message.text, Toast.LENGTH_SHORT).show();
    }

    private void navigateTo(Destination destination) {
        if(destination.destination != 0) Navigation.findNavController(getView()).navigate(destination.destination);
    }

    private void initRegisterListener() {
        binding.btnRegister.setOnClickListener(view1 -> viewModel.onRegisterClicked());
    }

    private void initSpinnerListener() {
        binding.spnBreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int selectedItemPosition, long l) {
                viewModel.onBreedSelected(binding.spnBreed.getAdapter().getItem(selectedItemPosition).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing
            }
        });
    }
}