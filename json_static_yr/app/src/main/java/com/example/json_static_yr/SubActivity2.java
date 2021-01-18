package com.example.json_static_yr;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.ExecutionException;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class SubActivity2 extends AppCompatActivity {
    public TextView select_year_month;
    public String select_ym;
    public String select_year;
    public String select_month;
    public static Context context;
    public float month_food;
    public float month_shop;
    public float month_life;
    public float month_culture;
    public float month_traffic;
    public float month_other;


    Button btnYearMonthPicker;
    Button btn_confirm;
    //number picker 값받아오기
    private TextView textView_Date;
    private TextView textView_Date_e;
    private DatePickerDialog.OnDateSetListener callbackMethod_e;


    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            select_year_month.setText(year + "년 " + month + "월");

            Log.d("YearMonthPickerTest", "year= " + year + ", month= " + month);


            select_year = String.valueOf(year);
            select_month = String.valueOf(month);
            if (select_month.length() == 1) {
                select_month = "0" + select_month;
            }
            select_ym = select_year + select_month;
            Log.d("select_ym", "select_ym = " + select_ym);

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
            public void onClick(View view) {
                MyYearMonthPicker pd = new MyYearMonthPicker();
                pd.setListener(d);
                pd.show(getSupportFragmentManager(), "YearMonthPickerTest");
            }
        });

        this.InitializeView();
        context = this;
        Button button = (Button) findViewById(R.id.btn_confirm);//년월선택
        Button btn = (Button) findViewById(R.id.check);//조회

        select_year_month = (TextView) findViewById(R.id.select_year_month);

        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resulttotalsum = "";
                String resultmonthfood = "";
                String resultmonthshop = "";
                String resultmonthlife = "";
                String resultmonthculture = "";
                String resultmonthtraffic = "";
                String resultmonthother = "";
                try {
                    resulttotalsum = new NewActivity2.MonthTotal().execute().get();
                    resultmonthfood = new NewActivity2.MonthFood().execute().get();
                    resultmonthshop = new NewActivity2.MonthShop().execute().get();
                    resultmonthlife = new NewActivity2.MonthLife().execute().get();
                    resultmonthculture = new NewActivity2.MonthCulture().execute().get();
                    resultmonthtraffic = new NewActivity2.MonthTraffic().execute().get();
                    resultmonthother = new NewActivity2.MonthOther().execute().get();

                    month_food = Float.parseFloat(resultmonthfood)/Float.parseFloat(resulttotalsum)*100;
                    month_shop = Float.parseFloat(resultmonthshop)/Float.parseFloat(resulttotalsum)*100;
                    month_life = Float.parseFloat(resultmonthlife)/Float.parseFloat(resulttotalsum)*100;
                    month_culture = Float.parseFloat(resultmonthculture)/Float.parseFloat(resulttotalsum)*100;
                    month_traffic = Float.parseFloat(resultmonthtraffic)/Float.parseFloat(resulttotalsum)*100;
                    month_other = Float.parseFloat(resultmonthother)/Float.parseFloat(resulttotalsum)*100;

                    Log.d("select_ym", month_food+" "+month_shop+" "+month_life +" "+month_culture);


                    BarChart chart = findViewById(R.id.barchart);
                    ArrayList NoOfEmp = new ArrayList();

                    NoOfEmp.add(new BarEntry(month_food, 0));
                    NoOfEmp.add(new BarEntry(month_shop, 1));
                    NoOfEmp.add(new BarEntry(month_life, 2));
                    NoOfEmp.add(new BarEntry(month_culture, 3));
                    NoOfEmp.add(new BarEntry(month_traffic, 4));
                    NoOfEmp.add(new BarEntry(month_other, 5));

                    ArrayList day = new ArrayList();

                    day.add("식비");
                    day.add("쇼핑");
                    day.add("생활");
                    day.add("문화/여가");
                    day.add("교통");
                    day.add("기타");



                    BarDataSet bardataset = new BarDataSet(NoOfEmp, "월별 통계(%)");
                    chart.animateY(5000);
                    BarData data = new BarData(day, bardataset);      // MPAndroidChart v3.X 오류 발생
                    bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                    chart.setData(data);



                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }

        });
    }
    public void InitializeView() {
        textView_Date = (TextView) findViewById(R.id.select_year_month);
    }



}