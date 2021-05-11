package com.teo.a53_multipletypesrecviewdemo;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class CustomViewHolder<T extends AdapterData> extends RecyclerView.ViewHolder {

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void setData(T data);
}
