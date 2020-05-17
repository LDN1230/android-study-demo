package ldn.layoutdemo;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        button1.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, LinearLayoutDemo.class);
            startActivity(intent);
        });
        button2.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, GridLayoutDemo.class);
            startActivity(intent);
        });
        button3.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, FrameLayoutDemo.class);
            startActivity(intent);
        });
        button4.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ConstraintLayoutDemo.class);
            startActivity(intent);
        });
    }
}
