package ldn.viewanimatordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class ViewFlipperTest extends AppCompatActivity {

    private ViewFlipper flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper_test);
        flipper = findViewById(R.id.details);
    }

    public void prev(View source){
        flipper.setInAnimation(this, R.anim.slide_in_right);
        flipper.setOutAnimation(this, R.anim.slide_out_left);
        flipper.showPrevious();
        flipper.stopFlipping();
    }

    public void next(View source){
        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
        flipper.showNext();
        flipper.stopFlipping();
    }

    public void auto(View source){
        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
        flipper.startFlipping();
    }
}
