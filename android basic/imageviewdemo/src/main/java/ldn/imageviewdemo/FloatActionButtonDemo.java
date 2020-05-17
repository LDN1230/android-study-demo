package ldn.imageviewdemo;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FloatActionButtonDemo extends AppCompatActivity {

    private boolean isShow = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_action_button_demo);
        FloatingActionButton fab = findViewById(R.id.fab);
        ConstraintLayout content = findViewById(R.id.content);
        fab.setOnClickListener(v->{
            switch (v.getId()){
                case R.id.fab:
                    isShow = !isShow;
                    content.setVisibility(isShow? View.VISIBLE: View.GONE);
                    break;
            }
        });
    }
}
