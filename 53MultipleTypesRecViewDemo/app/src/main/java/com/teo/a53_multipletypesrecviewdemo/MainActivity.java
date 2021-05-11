package com.teo.a53_multipletypesrecviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdapterData[] dataItems = new AdapterData[]{
                new Header("Currently Reading"),
                new Book("Sapiens", "Naval Harari", 450),
                new Book("Sprint", "Google", 252),
                new Book("7 Habits", "John Smith", 292),
                new Book("Factfulness", "Ivan Ivanov", 340),
                new Book("Harry Potter", "JK Rowling", 640),
                new Header("Already Finished"),
                new Book("Dandilion Wine", "Ray Bradbury", 333),
        };

        ((Book) dataItems[1]).setPagesReadCount(450);
        ((Book) dataItems[1]).setLastPageReadIndex(245);
        ((Book) dataItems[3]).setPagesReadCount(123);
        ((Book) dataItems[3]).setLastPageReadIndex(123);
        ((Book) dataItems[5]).setPagesReadCount(450);
        ((Book) dataItems[5]).setLastPageReadIndex(3);

        RecyclerView recyclerView = findViewById(R.id.recViewBooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BooksAdapter(dataItems));
    }
}