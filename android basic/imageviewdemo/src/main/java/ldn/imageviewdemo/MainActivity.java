package ldn.imageviewdemo;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        button1.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, PictureScanner.class);
            startActivity(intent);
        });
        button2.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, FunctionalImageButton.class);
            startActivity(intent);
        });
        button3.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, QuickContactBadgeDemo.class);
            startActivity(intent);
        });
        button4.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, FloatActionButtonDemo.class);
            startActivity(intent);
        });

    }
}
