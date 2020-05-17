package ldn.textviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class ToggleButtonSwitch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle_button_switch);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        Switch sw = findViewById(R.id.switch1);
        LinearLayout test = findViewById(R.id.test);
        CompoundButton.OnCheckedChangeListener listener = (bt, isChecked)->{
              if(isChecked){
                  test.setOrientation(LinearLayout.VERTICAL);
                  toggleButton.setChecked(true);
                  sw.setChecked(true);
              }else {
                  test.setOrientation(LinearLayout.HORIZONTAL);
                  toggleButton.setChecked(false);
                  sw.setChecked(false);
              }
        };
        toggleButton.setOnCheckedChangeListener(listener);
        sw.setOnCheckedChangeListener(listener);
    }
}
