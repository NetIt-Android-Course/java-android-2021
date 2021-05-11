package com.teo.a53_multipletypesrecviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BooksAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private AdapterData[] data;
    private static final int TYPE_BOOK = 1;
    private static final int TYPE_HEADER = 2;

    public BooksAdapter(AdapterData[] books) {
        data = books;
    }

    @Override
    public int getItemViewType(int position) {
        AdapterData dataItem = data[position];
        if(dataItem instanceof Book) return TYPE_BOOK;
        if(dataItem instanceof Header) return TYPE_HEADER;
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_BOOK) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_book, parent, false);
            return new BookViewHolder(view);
        } else if(viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_header, parent, false);
            return new HeaderViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        AdapterData dataItem = data[position];
        holder.setData(dataItem);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
