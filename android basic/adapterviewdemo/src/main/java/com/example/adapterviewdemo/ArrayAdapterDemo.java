package com.example.adapterviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayAdapterDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter_demo);
        ListView list1 = findViewById(R.id.list1);
        String[] arr1 = new String[]{"孙悟空", "猪八戒", "牛魔王"};
        ArrayAdapter adapter1 = new ArrayAdapter(this, R.layout.array_item1, arr1);
        list1.setAdapter(adapter1);

        ListView list2 = findViewById(R.id.list2);
        String[] arr2 = new String[]{"Java", "C++", "Python"};
        ArrayAdapter adapter2 = new ArrayAdapter(this, R.layout.array_item2, arr2);
        list2.setAdapter(adapter2);
    }
}
