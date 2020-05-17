package com.example.adapterviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class AdapterViewFlipperDemo extends AppCompatActivity {

    private int[] imageIds = new int[]{R.drawable.dog, R.drawable.cat, R.drawable.pig};
    private AdapterViewFlipper flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_view_flipper_demo);
        flipper = findViewById(R.id.flipper);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return imageIds.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if (convertView == null){
                    imageView = new ImageView(AdapterViewFlipperDemo.this);
                }else {
                    imageView = (ImageView)convertView;
                }
                imageView.setImageResource(imageIds[position]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        };
        flipper.setAdapter(adapter);
    }

    public void prev(View source){
        flipper.showPrevious();
        flipper.stopFlipping();
    }

    public void next(View source){
        flipper.showNext();
        flipper.stopFlipping();
    }

    public void auto(View source){
        flipper.startFlipping();
    }
}
