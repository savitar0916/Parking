package com.example.parking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.RadioGroup;

import com.example.parking.Adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private RadioGroup rgGroup;
    private List<Fragment> fragments;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.vp_main);

        fragments=new ArrayList<Fragment>();

        fragments.add(new ParkFragment());
        fragments.add(new SchoolFragment());
        fragments.add(new RecordFragment());
        fragments.add(new ClockFragment());
        fragments.add(new SettingFragment());

        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(myFragmentPagerAdapter);

        rgGroup = (RadioGroup) findViewById(R.id.rg_group);
        rgGroup.check(R.id.rb_parking);
        //點底部按鍵換頁
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_parking) {
                    mViewPager.setCurrentItem(0, false);//去掉切換動畫
                } else if (i == R.id.rb_school) {
                    mViewPager.setCurrentItem(1, false);
                } else if (i == R.id.rb_record) {
                    mViewPager.setCurrentItem(2, false);
                } else if (i == R.id.rb_clock) {
                    mViewPager.setCurrentItem(3, false);
                } else if (i == R.id.rb_setting) {
                    mViewPager.setCurrentItem(4, false);
                }
            }
        });
        //防止頻繁的一直換頁
        mViewPager.setOffscreenPageLimit(4);
    }
}
