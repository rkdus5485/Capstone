package com.example.json_array;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView list_trandate =(TextView)findViewById(R.id.trandate);
        TextView list_inout_type =(TextView)findViewById(R.id.inout_type);
        TextView list_tran_type =(TextView)findViewById(R.id.tran_type);
        TextView list_print_content =(TextView)findViewById(R.id.print_content);
        TextView list_tran_amt =(TextView)findViewById(R.id.tran_amt);

        String resultText1 ="";
        String resultText2 ="";
        String resultText3 ="";
        String resultText4 ="";
        String resultText5 ="";


        try{
            resultText1 = new Task().execute().get();
            resultText2 = new Task2().execute().get();
            resultText3 = new Task3().execute().get();
            resultText4 = new Task4().execute().get();
            resultText5 = new Task5().execute().get();


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
}