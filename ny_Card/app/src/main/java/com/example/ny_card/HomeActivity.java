package com.example.ny_card;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag2);

        list = (ListView) findViewById(R.id.list);

        List<String> data = new ArrayList<>(); //데이터를 스트링 형태로 나오게 한다

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        list.setAdapter(adapter);

        data.add("나영");
        data.add("안드로이드");
        adapter.notifyDataSetChanged();//위 데이터들을 저장한다.


    }
}
