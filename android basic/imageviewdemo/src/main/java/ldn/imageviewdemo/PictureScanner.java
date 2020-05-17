package ldn.imageviewdemo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PictureScanner extends AppCompatActivity {

    private int[] images = new int[]{R.drawable.we1, R.drawable.we2, R.drawable.we3};
    private int currentImg = 2;
    private int alpha = 255;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_scanner);
        Button plus = findViewById(R.id.plus);
        Button minus = findViewById(R.id.minus);
        Button next = findViewById(R.id.next);
        ImageView image1 = findViewById(R.id.imageView1);
        ImageView image2 = findViewById(R.id.imageView2);
        next.setOnClickListener(v->{
            image1.setImageResource(images[++currentImg % images.length]);
        });
        View.OnClickListener listener = v->{
            if (v == plus){
                alpha += 20;
            }
            if (v == minus){
                alpha -= 20;
            }
            if (alpha >= 255){
                alpha = 255;
            }
            if (alpha <= 0){
                alpha = 0;
            }
            image1.setImageAlpha(alpha);
        };
        plus.setOnClickListener(listener);
        minus.setOnClickListener(listener);
        image1.setOnTouchListener((view, event)->{
            BitmapDrawable bitmapDrawable = (BitmapDrawable) image1.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            double scale = 1.0 * bitmap.getHeight() / image1.getHeight();
            long x = Math.round(event.getX() * scale);
            long y = Math.round(event.getY() * scale);
            if (x + 120 > bitmap.getWidth()){
                x = bitmap.getWidth() - 120;
            }
            if (y + 120 > bitmap.getHeight()){
                y = bitmap.getHeight() - 120;
            }
            image2.setImageBitmap(Bitmap.createBitmap(bitmap, (int)x, (int)y, 120, 120));
            image2.setImageAlpha(alpha);
            return false;
        });
    }
}
