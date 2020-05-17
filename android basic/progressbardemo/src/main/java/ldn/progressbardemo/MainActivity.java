package ldn.progressbardemo;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    class myClickListener implements View.OnClickListener {
        Intent intent;
        Class c;
        public myClickListener(Class c){
            this.c = c;
        }
        @Override
        public void onClick(View v) {
            this.intent = new Intent();
            intent.setClass(MainActivity.this, c);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        button1.setOnClickListener(new myClickListener(ProgressBarTest.class));
        button2.setOnClickListener(new myClickListener(SeekBarTest.class));
        button3.setOnClickListener(new myClickListener(RatingBarTest.class));


    }
}
