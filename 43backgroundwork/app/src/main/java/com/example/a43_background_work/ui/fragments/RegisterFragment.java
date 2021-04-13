package com.example.a43_background_work.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.a43_background_work.R;
import com.example.a43_background_work.controllers.RegisterController;
import com.example.a43_background_work.databinding.FragmentRegisterBinding;
import com.example.a43_background_work.ui.adapters.ImageUrlsAdapter;
import com.example.a43_background_work.ui.models.RegisterViewModel;

import java.util.List;

public class RegisterFragment extends Fragment {

    private List<String> breeds;
    private RegisterController controller;
    private FragmentRegisterBinding binding;
    private RegisterViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new RegisterViewModel();
        binding.setRegmodel(model);

        controller = new RegisterController(callback);
        controller.showErrorLiveData.observe(getViewLifecycleOwner(),
                s -> Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show());

        initSpinnerListener();
        initRegisterListener();
        controller.onUiLoaded();
    }

    private void initRegisterListener() {
        binding.btnRegister.setOnClickListener(view1 -> controller.onRegisterClicked(model));
    }

    private void initSpinnerListener() {
        binding.spnBreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedBreed = breeds.get(i);
                controller.onBreedSelected(selectedBreed);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing
            }
        });
    }

    private void setRecViewData(String[] data) {
        binding.recImages.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.recImages.setAdapter(new ImageUrlsAdapter(data));
    }

    private RegisterController.RegisterControllerCallback callback = new RegisterController.RegisterControllerCallback() {
        @Override
        public void showImageLinks(String[] data) {
            setRecViewData(data);
        }

        @Override
        public void showBreeds(List<String> data) {
            breeds = data;
            binding.spnBreed.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, breeds));
        }

//        @Override
//        public void showError(String error) {
//            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
//        }

        @Override
        public void showMessage(String message) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void navigateToLoginScreen() {
            getActivity().onBackPressed();
        }
    };
}