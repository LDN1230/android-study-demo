package ldn.textviewdemo;



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
        button1.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, TextViewExample.class);
            startActivity(intent);
        });
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ButtonExample.class);
            startActivity(intent);
        });

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, RadioButtionCheckBox.class);
            startActivity(intent);
        });

        Button button11 = findViewById(R.id.button11);
        button11.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ToggleButtonSwitch.class);
            startActivity(intent);
        });

        Button button12 = findViewById(R.id.button12);
        button12.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, AnalogClockTextClock.class);
            startActivity(intent);
        });
    }
}
