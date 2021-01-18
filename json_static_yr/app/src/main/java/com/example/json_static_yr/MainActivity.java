package com.example.json_static_yr;


import android.animation.ArgbEvaluator;
import android.content.Intent;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int sub = 1001;

    ViewPager viewPager;
    Adapter adapter;

    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.list_image, "소비 내역(list)", "기간을 설정하여 카드 사용 내역을 확인하세요"));
        models.add(new Model(R.drawable.list_image, "소비 내역(통계)","기간을 설정하여 카드 사용 내역을 확인하세요"));
        models.add(new Model(R.drawable.statistic_image, "소비 통계", "한달 사용 내역 비율을 알아보세요"));


        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,360,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1)
        };
        colors = colors_temp;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));

                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}