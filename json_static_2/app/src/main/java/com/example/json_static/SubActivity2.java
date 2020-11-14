package com.example.json_static;

import android.accessibilityservice.FingerprintGestureController;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class SubActivity2 extends AppCompatActivity {
    public TextView totalsum;
    public TextView select_year_month;

    public String select_ym;
    public String select_year;
    public String select_month;

    Button btnYearMonthPicker;
    Button btn_confirm;
    //number picker 값받아오기


    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Log.d("YearMonthPickerTest", "year= "+year+", month= "+month);


            select_year = String.valueOf(year);
            select_month = String.valueOf(month);
            if(select_month.length()==1){
                select_month="0"+select_month;
            }
            select_ym = select_year+select_month;

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnYearMonthPicker = findViewById(R.id.btn_year_month_picker);
        btnYearMonthPicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                MyYearMonthPicker pd = new MyYearMonthPicker();
                pd.setListener(d);
                pd.show(getSupportFragmentManager(),  "YearMonthPickerTest");
            }
        });
        Button button =(Button)findViewById(R.id.btn_confirm);//년월선택
        Button btn = (Button) findViewById(R.id.check);//조회

        select_year_month = (TextView) findViewById(R.id.select_year_month);
        totalsum = (TextView) findViewById(R.id.totalsum);

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_year_month.setText(select_year+"년 "+select_month+"월");
                                      }
                                  });
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resulttotalsum = "";
                try {
                    resulttotalsum = new NewActivity2.MonthTotal().execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                totalsum.setText(resulttotalsum);
            }
        });

    }
}