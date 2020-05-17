package ldn.imageviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.QuickContactBadge;

public class QuickContactBadgeDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_contact_badge_demo);
        QuickContactBadge badge = findViewById(R.id.badge);
        badge.assignContactFromPhone("15013140428", false);
    }
}
