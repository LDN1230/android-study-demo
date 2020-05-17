package com.example.adapterviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class AutoCompleteTextViewDemo extends AppCompatActivity {

    private String[] books = new String[]{"Book1 Java", "Book2 C++", "Book3 python", "Book4 go"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view_demo);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, books);
        AutoCompleteTextView auto = findViewById(R.id.auto);
        auto.setAdapter(adapter);
        MultiAutoCompleteTextView muto = findViewById(R.id.muto);
        muto.setAdapter(adapter);
        muto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
