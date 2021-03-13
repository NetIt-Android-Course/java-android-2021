package com.example.a43_background_work.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a43_background_work.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageUrlsAdapter extends RecyclerView.Adapter<ImageUrlViewHolder> {

    private String[] urls;
    private List<String> selectedUrls;

    public ImageUrlsAdapter(String[] urls) {
        this.urls = urls;
        this.selectedUrls = new ArrayList<>();
    }

    @NonNull
    @Override
    public ImageUrlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image,
                parent,
                false);
        return new ImageUrlViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageUrlViewHolder holder, int position) {
        ImageView imageView = holder.itemView.findViewById(R.id.imageView);
        Picasso.get().load(urls[position]).into(imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int visibility = holder.itemView.findViewById(R.id.imgCheck).getVisibility();
                if(visibility == View.VISIBLE) {
                    holder.itemView.findViewById(R.id.imgCheck).setVisibility(View.INVISIBLE);
                    selectedUrls.remove(urls[position]);
                } else {
                    holder.itemView.findViewById(R.id.imgCheck).setVisibility(View.VISIBLE);
                    selectedUrls.add(urls[position]);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return urls.length;
    }

    public List<String> getSelectedUrls() {
        return selectedUrls;
    }
}
