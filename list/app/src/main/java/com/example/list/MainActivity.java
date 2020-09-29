package com.example.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list1;
    private ListView list2;
    private ListView list3;
    private ListView list4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list1 = (ListView)findViewById(R.id.date);
        List<String> data1 = new ArrayList<>();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.list_layout,data1);
        list1.setAdapter(adapter1);

        list2 = (ListView)findViewById(R.id.option);
        List<String> data2 = new ArrayList<>();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,R.layout.list_layout,data2);
        list2.setAdapter(adapter2);

        list3 = (ListView)findViewById(R.id.use);
        List<String> data3 = new ArrayList<>();
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,R.layout.list_layout,data3);
        list3.setAdapter(adapter3);

        list4 = (ListView)findViewById(R.id.price);
        List<String> data4 = new ArrayList<>();
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,R.layout.list_layout,data4);
        list4.setAdapter(adapter4);

        data1.add("날짜");
        data2.add("구분");
        data3.add("내역");
        data4.add("금액");

        data1.add("2020-09-19");
        data2.add("지출");
        data3.add("커피");
        data4.add("3000원");

        data1.add("2020-09-21");
        data2.add("지출");
        data3.add("점심");
        data4.add("7000원");
    }
}