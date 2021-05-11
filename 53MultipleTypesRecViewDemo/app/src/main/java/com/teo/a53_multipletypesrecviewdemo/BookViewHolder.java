package com.teo.a53_multipletypesrecviewdemo;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookViewHolder extends CustomViewHolder<Book> {

    private TextView txtName;
    private TextView txtAuthor;
    private TextView txtPercent;
    private TextView txtTotals;
    private TextView txtLastPage;

    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        this.txtName = itemView.findViewById(R.id.txtName);
        this.txtAuthor = itemView.findViewById(R.id.txtAuthor);
        this.txtPercent = itemView.findViewById(R.id.txtPercentageRead);
        this.txtTotals = itemView.findViewById(R.id.txtPagesReadTotal);
        this.txtLastPage = itemView.findViewById(R.id.txtLastPageRead);
    }

    @Override
    public void setData(Book book) {
        String pagesReadFromTotal = itemView.getContext().getString(R.string.x_pages_read_from_y, book.getPagesReadCount(), book.getTotalPageCount());
        String lastPageRead = itemView.getContext().getString(R.string.last_page_read_x, book.getLastPageReadIndex());

        txtName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPercent.setText((int) Math.floor((book.getPagesReadCount() * 100.0f) / book.getTotalPageCount()) + "%");
        txtTotals.setText(pagesReadFromTotal);
        txtLastPage.setText(lastPageRead);
    }
}
