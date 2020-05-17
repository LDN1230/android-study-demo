package ldn.viewanimatordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageSwitcherTest extends AppCompatActivity {

    int[] imageIds = new int[]{R.drawable.cat, R.drawable.dog, R.drawable.tiger, R.drawable.pig,
            R.drawable.libai, R.drawable.liqingzhao, R.drawable.nongyu};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switcher_test);
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i=0; i<imageIds.length; i++){
            Map<String, Object> item = new HashMap<>();
            item.put("image", imageIds[i]);
            listItems.add(item);
        }
        ImageSwitcher switcher = findViewById(R.id.switcher);
        switcher.setFactory(()->{
            ImageView imageView = new ImageView(ImageSwitcherTest.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT));
            return imageView;
        });
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.cell, new String[]{"image"},
                new int[]{R.id.image1});
        GridView grid = findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switcher.setImageResource(imageIds[position]);
            }
        });
        grid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switcher.setImageResource(imageIds[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
