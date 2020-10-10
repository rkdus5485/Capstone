package com.example.json_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private List<HashMap<String, String>> photoinfoList = null;
    private ProgressDialog progressDialog = null;
    private SimpleAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.parsetext);
        String resultText = "X";

        try{
            resultText = new Task().execute().get();

        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        textView.setText(resultText);

    }
    public static class Task extends AsyncTask<String[], Void, String>{
        String clientKey = "";
        private String str;
        private String receiveMsg;
        private final String ID = "";


        @Override
        protected String doInBackground(String[]... params){
            //U +"  "<-여기에 올 9자리 난수 생성
            Random rand = new Random();
            String numStr = "";

            for(int i=0;i<9;i++) {
                String ran = Integer.toString(rand.nextInt(9));
                numStr += ran;
            }
            String header = "Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzY0MDIyIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MDk5OTQzODcsImp0aSI6IjY2MTgzYjg3LTEyNTUtNDcwMi1iMDEwLTY2MzUzNDlmMTc5OCJ9.O6UnAju-wZkbywcvHe0GVlSxTkFuGODpY137DnGMMhw";

            URL url = null;
            String A = "https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991663280U"+numStr+"&fintech_use_num=199166328057887381558426&inquiry_type=A&inquiry_base=D&from_date=20190101&to_date=20201007&sort_order=D&tran_dtime=20201006025351";
            try{
                Log.d("a", A);
                url = new URL("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991663280U"+numStr+"&fintech_use_num=199166328057887381558426&inquiry_type=A&inquiry_base=D&from_date=20190101&to_date=20201007&sort_order=D&tran_dtime=20201006025351");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", header);
                if(conn.getResponseCode() == conn.HTTP_OK){
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();

                    while((str=reader.readLine()) != null){
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.i("receiveMsg : ",receiveMsg);
                    reader.close();
                }else{
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return listjsonParser(receiveMsg);
        }

        public String listjsonParser(String jsonString){
            String tran_date = null;
            String tran_time = null;
            String inout_type = null;
            String tran_type = null;
            String print_content = null;
            String tran_amt = null;
            String after_balance_amt = null;
            String branch_name = null;
            String result = "";
            String[] a = new String[100];


            String[] arraysum = new String[5];
            try{
                JSONArray jarray = new JSONObject(jsonString).getJSONArray("res_list");
                for(int i=0;i<jarray.length();i++){
                    HashMap map = new HashMap<>();
                    JSONObject jObject = jarray.getJSONObject(i);

                    tran_date = jObject.optString("tran_date");
                    tran_time = jObject.optString("tran_time");
                    inout_type = jObject.optString("inout_type");
                    tran_type = jObject.optString("tran_type");
                    print_content = jObject.optString("print_content");
                    tran_amt = jObject.optString("tran_amt");
                    after_balance_amt = jObject.optString("after_balance_amt");
                    branch_name = jObject.optString("branch_name");

                    arraysum[0]=tran_date;
                    arraysum[1]=inout_type;
                    arraysum[2] = tran_type;
                    arraysum[3] = print_content;
                    arraysum[4]=tran_amt;


                    a[i] = Arrays.toString(arraysum);

                    StringBuilder sb = new StringBuilder();
                    for(String ch : arraysum){
                        sb.append(ch+"\t"+"\t"+"\t");
                    }
                    a[i] = sb.toString();

                    //String b=String.valueOf(a);
                    //result += b+"\n";

                    //String str = new String(arraysum);

                    result += a[i]+"\n";


                }
                //result = Arrays.toString(arraysum);
            }catch (JSONException e){
                e.printStackTrace();
            }
            return result;
        }

    }
}
