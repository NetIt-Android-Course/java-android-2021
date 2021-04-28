package com.example.a43_background_work.features;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a43_background_work.features.register.adapters.ImageUrlsAdapter;

import java.util.List;

public class BindingAdapters {

    @BindingAdapter("spinnerValues")
    public static void setSpinnerValues(Spinner spinner, List<String> values) {
        if(values != null)
            spinner.setAdapter(new ArrayAdapter<String>(spinner.getContext(), android.R.layout.simple_list_item_1, values));
    }

    @BindingAdapter("selectedSpinnerValue")
    public static void setSelectedSpinnerValue(Spinner spinner, String value) {
        if(value != null)
            spinner.setSelection(((ArrayAdapter) spinner.getAdapter()).getPosition(value));
    }

    @BindingAdapter("photoLinks")
    public static void setPhotoLinks(RecyclerView recyclerView, String[] values) {
        if(values == null) return;
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 3));
        recyclerView.setAdapter(new ImageUrlsAdapter(values));
    }
}
