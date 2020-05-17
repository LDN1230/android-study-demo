package com.example.adapterviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.StackView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StackViewDemo extends AppCompatActivity {

    private  int[] imageInds = new int[]{R.drawable.dog, R.drawable.cat, R.drawable.pig};
    StackView stackView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack_view_demo);
        stackView = findViewById(R.id.stackView);
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i=0; i<imageInds.length; i++){
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("image", imageInds[i]);
            listItems.add(listItem);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.cell, new String[]{"image"}, new int[]{R.id.image1});
        stackView.setAdapter(adapter);
    }

    public void prev(View view){
        stackView.showPrevious();
    }

    public void next(View view){
        stackView.showNext();
    }
}
