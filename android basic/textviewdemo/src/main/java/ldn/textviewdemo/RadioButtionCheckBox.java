package ldn.textviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioButtionCheckBox extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_buttion_check_box);
        RadioGroup rg = findViewById(R.id.rg);
        TextView show = findViewById(R.id.show);
        rg.setOnCheckedChangeListener((radioGroup, checkedId)->{
            String printSex = checkedId == R.id.male ? "您的性别是男人" : "您的性别是女人";
            show.setText(printSex);
        });
    }
}
