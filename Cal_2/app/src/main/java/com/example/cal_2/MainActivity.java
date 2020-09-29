package com.example.cal_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    GridView monthView;
    TextView monthText;
    MonthAdapter adt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthView = findViewById(R.id.monthView);//그리드뷰 객체 참조
        adt = new MonthAdapter(this);//어댑터 객체 생성
        monthView.setAdapter(adt);//그리드뷰에 어댑터 설정

        monthText = findViewById(R.id.monthText);
        setMonthText();

        Button monthPrevious = findViewById(R.id.monthPrevious);
        Button monthNext = findViewById(R.id.monthNext);

        //뒤로가기 버튼 이벤트 리스너 설정
        monthPrevious.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                adt.setPreviousMonth();
                adt.notifyDataSetChanged();//어댑터 데이터 갱신하고 뷰 다시 뿌리기
                setMonthText();
            }
        });

        //앞으로 가기 버튼에 이벤트 리스너 설정
        monthNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                adt.setNextMonth();
                adt.notifyDataSetChanged();
                setMonthText();
            }
        });
    }
    public void setMonthText(){
        int curYear = adt.getCurYear();
        int curMonth = adt.getCurMonth();
        monthText.setText(curYear+"."+(curMonth+1));
    }
}
