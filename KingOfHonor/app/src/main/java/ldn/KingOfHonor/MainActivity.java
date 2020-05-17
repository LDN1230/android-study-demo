package ldn.KingOfHonor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import ldn.KingOfHonor.fragment.Fragment1;
import ldn.KingOfHonor.fragment.Fragment2;
import ldn.KingOfHonor.fragment.Fragment3;
import ldn.KingOfHonor.fragment.Fragment4;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //添加各个页面
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.title1, Fragment1.class)
                .add(R.string.title2, Fragment2.class)
                .add(R.string.title3, Fragment3.class)
                .add(R.string.title4, Fragment4.class)
                .create());
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }
}
