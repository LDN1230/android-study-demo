package ldn.selfdefineview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        DrawView drawView = new DrawView(this);
        drawView.setMinimumHeight(500);
        drawView.setMinimumWidth(300);
    }
}
