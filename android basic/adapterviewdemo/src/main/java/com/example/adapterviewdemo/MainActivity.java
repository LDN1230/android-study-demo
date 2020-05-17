package com.example.adapterviewdemo;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(view->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ArrayAdapterDemo.class);
            startActivity(intent);
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(view->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, SimpleAdapterDemo.class);
            startActivity(intent);
        });
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(view->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, AutoCompleteTextViewDemo.class);
            startActivity(intent);
        });
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(view->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ExpandableListViewDemo.class);
            startActivity(intent);
        });
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(view->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, AdapterViewFlipperDemo.class);
            startActivity(intent);
        });
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(view->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, StackViewDemo.class);
            startActivity(intent);
        });
        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(view->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, RecyclerViewDemo.class);
            startActivity(intent);
        });
    }
}
