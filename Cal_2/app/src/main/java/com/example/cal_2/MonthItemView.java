package com.example.cal_2;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

public class MonthItemView extends AppCompatTextView {
    private MonthItem item;

    public MonthItemView(Context context){
        super(context);
        init();
    }

    public MonthItemView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    private void init(){
        setBackgroundColor(Color.WHITE);
    }

    public void setItem(MonthItem item) {

        this.item = item;

        int month = item.getMonth();

        int day = item.getDay();
            if (day != 0) {
                setText(String.valueOf(day));
                setGravity(Gravity.LEFT);
                setTextColor(Color.BLACK);
                setTextSize(15);
                if (day == 3) {
                    setText(String.valueOf(day) + "\n+500");

                }
            } else {
                setText("");
            }
        }

}
