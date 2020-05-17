package ldn.progressbardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;

public class RatingBarTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar_test);
        ImageView image = findViewById(R.id.cat);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser)->{
            image.setImageAlpha((int)(rating * 255 / 5));
        });
    }
}
