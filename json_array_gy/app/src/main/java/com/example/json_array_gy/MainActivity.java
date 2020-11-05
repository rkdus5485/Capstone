package com.example.json_array_gy;

import android.accessibilityservice.FingerprintGestureController;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    public String p_start;
    public String p_end;
    public String s_start;
    public String s_end;
    public String s_year;
    public String e_year;
    public String s_month;
    public String e_month;
    public String s_day;
    public String e_day;
    private TextView textView_Date;
    private TextView textView_Date_e;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    private DatePickerDialog.OnDateSetListener callbackMethod_e;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/M/d");
    public static Context context;
    public TextView list_trandate;
    public TextView list_inout_type;
    public TextView list_tran_type;
    public TextView list_print_content;
    public TextView list_tran_amt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        this.InitializeListener();
        this.InitializeView();
        this.InitializeListener_e();
        this.InitializeView_e();

        Button btn = (Button)findViewById(R.id.btn_onClick);

        list_trandate =(TextView)findViewById(R.id.trandate);
        list_inout_type =(TextView)findViewById(R.id.inout_type);
        list_tran_type =(TextView)findViewById(R.id.tran_type);
        list_print_content =(TextView)findViewById(R.id.print_content);
        list_tran_amt =(TextView)findViewById(R.id.tran_amt);



        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){

                if(Integer.parseInt(s_start)>Integer.parseInt(s_end)){
                    Toast myToast = Toast.makeText(getApplicationContext(),"시작 날짜가 마지막 날짜보다 후로 선택됨", Toast.LENGTH_SHORT);
                    myToast.show();
                }else if((Integer.parseInt(s_end)>Integer.parseInt(p_start))||(Integer.parseInt(s_start)>Integer.parseInt(p_start))){
                    Toast myToast = Toast.makeText(getApplicationContext(),"오늘 이전의 날짜를 선택하시오.", Toast.LENGTH_SHORT);
                    myToast.show();
                }

                String resultText1 ="";
                String resultText2 ="";
                String resultText3 ="";
                String resultText4 ="";
                String resultText5 ="";


                try{
                    resultText1 = new NewActivity.Task1().execute().get();
                    resultText2 = new NewActivity.Task2().execute().get();
                    resultText3 = new NewActivity.Task3().execute().get();
                    resultText4 = new NewActivity.Task4().execute().get();
                    resultText5 = new NewActivity.Task5().execute().get();



                } catch (InterruptedException e){
                    e.printStackTrace();
                } catch (ExecutionException e){
                    e.printStackTrace();
                }

                list_trandate.setText(resultText1);
                list_inout_type.setText(resultText2);
                list_tran_type.setText(resultText3);
                list_print_content.setText(resultText4);
                list_tran_amt.setText(resultText5);
            }
        });
    }

    public void InitializeView(){
        textView_Date = (TextView)findViewById(R.id.textView_date_start);
    }

    public void InitializeView_e(){
        textView_Date_e = (TextView)findViewById(R.id.textView_date_end);
    }

    public void InitializeListener(){
        callbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                textView_Date.setText(year+"년 "+(monthOfYear+1)+"월 "+dayOfMonth+"일 ");

                s_year= String.valueOf(year);
                s_month= String.valueOf(monthOfYear+1);
                s_day= String.valueOf(dayOfMonth);
                /*if(length==1){
                    e_day = "0"+Integer.toString(dayOfMonth);
                }else{
                    e_day= Integer.toString(dayOfMonth);
                }*/
                if(s_day.length()==1){
                    s_day="0"+s_day;
                }
                if(s_month.length()==1){
                    s_month="0"+s_month;
                }
                s_start=s_year+s_month+s_day;
            }
        };
    }

    public void InitializeListener_e(){
        callbackMethod_e = new DatePickerDialog.OnDateSetListener() {
            @Override

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                textView_Date_e.setText(year+"년 "+(monthOfYear+1)+"월 "+dayOfMonth+"일");

                e_year= String.valueOf(year);
                e_month= String.valueOf(monthOfYear+1);
                e_day= String.valueOf(dayOfMonth);
                /*if(length==1){
                    e_day = "0"+Integer.toString(dayOfMonth);
                }else{
                    e_day= Integer.toString(dayOfMonth);
                }*/
                if(e_day.length()==1){
                    e_day="0"+e_day;
                }
                if(e_month.length()==1){
                    e_month="0"+e_month;
                }
                s_end=e_year+e_month+e_day;
            }
        };
    }
    public void OnClickHandler(View view){
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        String year_ = yearFormat.format(currentTime);
        String month_ = monthFormat.format(currentTime);
        String day_ = dayFormat.format(currentTime);

        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

        StringBuilder start = new StringBuilder();
        start.append(yearFormat);
        start.append(monthFormat);
        start.append(dayFormat);
        String date_ = date.format(currentTime);

        //p_start = start.toString();
        p_start = date_;

        int year = Integer.parseInt(year_);
        int month = Integer.parseInt(month_);
        int day = Integer.parseInt(day_);


        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, year, month-1, day);
        dialog.show();
    }

    public void OnClickHandler_e(View view){
        Date currentTime_e = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat_e = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat_e = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat_e = new SimpleDateFormat("yyyy", Locale.getDefault());
        String year_ = yearFormat_e.format(currentTime_e);
        String month_ = monthFormat_e.format(currentTime_e);
        String day_ = dayFormat_e.format(currentTime_e);
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());


        StringBuilder end = new StringBuilder();
        end.append(yearFormat_e);
        end.append(monthFormat_e);
        end.append(dayFormat_e);

        String date_e=date.format(currentTime_e);

        //p_end = end.toString();

        p_end=date_e;
        int year_e = Integer.parseInt(year_);
        int month_e = Integer.parseInt(month_);
        int day_e = Integer.parseInt(day_);





        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod_e, year_e, month_e-1, day_e);
        dialog.show();
    }
}