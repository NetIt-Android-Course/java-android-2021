package com.teo.a53_multipletypesrecviewdemo;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HeaderViewHolder extends CustomViewHolder<Header> {

    private final TextView txtHeader;

    public HeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        txtHeader = itemView.findViewById(R.id.txtHeader);
    }

    @Override
    public void setData(Header header) {
        txtHeader.setText(header.getText());
    }
}