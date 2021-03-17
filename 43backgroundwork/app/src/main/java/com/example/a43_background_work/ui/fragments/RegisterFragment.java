package com.example.a43_background_work.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a43_background_work.DogTinderApplication;
import com.example.a43_background_work.R;
import com.example.a43_background_work.ThreadUtils;
import com.example.a43_background_work.data.local.AppDatabase;
import com.example.a43_background_work.data.local.AsyncDatabase;
import com.example.a43_background_work.data.local.entities.UserEntity;
import com.example.a43_background_work.data.remote.ApiWrapper;
import com.example.a43_background_work.ui.adapters.ImageUrlsAdapter;

import java.util.Arrays;
import java.util.List;

public class RegisterFragment extends Fragment {

    private EditText edtName;
    private EditText edtAge;
    private Spinner spnBreed;
    private RecyclerView recImages;
    private Button btnRegister;
    private List<String> breeds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        loadBreeds();
        initSpinnerListener();
        view.findViewById(R.id.btnRegister).setOnClickListener(view1 -> onRegisterClicked());
    }

    private void onRegisterClicked() {
        List<String> selectedUrls = ((ImageUrlsAdapter) recImages.getAdapter()).getSelectedUrls();

        UserEntity userEntity = new UserEntity();
        userEntity.name = edtName.getText().toString();
        userEntity.age = Integer.parseInt(edtAge.getText().toString());
        userEntity.breed = breeds.get(spnBreed.getSelectedItemPosition());

        AsyncDatabase.getInstance(getContext()).getUserByNameAndAge(userEntity.name, userEntity.age, new AsyncDatabase.DataReceivedListener<UserEntity>() {
            @Override
            public void onDataReceived(UserEntity data) {
                if(data == null) {
                    AsyncDatabase.getInstance(getContext()).insert(userEntity);
                } else {
                    Toast.makeText(getContext(), "This user already exists. Login.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initSpinnerListener() {
        spnBreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedBreed = breeds.get(i);
                Toast.makeText(getContext(), "Selected breed: " + selectedBreed, Toast.LENGTH_SHORT).show();
                showBreedImages(selectedBreed);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing
            }
        });
    }

    private void showBreedImages(String selectedBreed) {
        ApiWrapper.getInstance().getImagesUrlByBreed(selectedBreed, new ApiWrapper.OnApiResultListener<String[]>() {
            @Override
            public void onSuccess(String[] data) {
                setRecViewData(data);
            }

            @Override
            public void onFailure() {
                Toast.makeText(getContext(), "Breeds failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecViewData(String[] data) {
        recImages.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recImages.setAdapter(new ImageUrlsAdapter(data));
    }

    private void loadBreeds() {
        ApiWrapper.getInstance().getAllBreeds(new ApiWrapper.OnApiResultListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                breeds = data;
                spnBreed.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, breeds));
            }

            @Override
            public void onFailure() {
                Toast.makeText(getContext(), "Breeds failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews(View view) {
        edtName = view.findViewById(R.id.edtName);
        edtAge = view.findViewById(R.id.edtAge);
        spnBreed = view.findViewById(R.id.spnBreed);
        recImages = view.findViewById(R.id.recImages);
        btnRegister = view.findViewById(R.id.btnRegister);
    }
}