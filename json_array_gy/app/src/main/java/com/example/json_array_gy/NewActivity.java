package com.example.json_array_gy;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

public class NewActivity {

    public static class Task1 extends AsyncTask<String, Void, String> {
        private String str, receiveMsg;
        String start = ((MainActivity) MainActivity.context).s_start;
        String end = ((MainActivity) MainActivity.context).s_end;

        @Override
        protected String doInBackground(String... params) {
            Random rand = new Random();
            String numStr = "";

            for (int i = 0; i < 9; i++) {
                String ran = Integer.toString(rand.nextInt(9));
                numStr += ran;
            }
            String header = "Bearer" + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzY0MDIyIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MDk5OTQzODcsImp0aSI6IjY2MTgzYjg3LTEyNTUtNDcwMi1iMDEwLTY2MzUzNDlmMTc5OCJ9.O6UnAju-wZkbywcvHe0GVlSxTkFuGODpY137DnGMMhw";
            URL url = null;
            try {
                url = new URL("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991663280U" + numStr + "&fintech_use_num=199166328057887381558426&inquiry_type=A&inquiry_base=D&from_date=" + start + "&to_date=" + end + "&sort_order=D&tran_dtime=20201006025351");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", header);

                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.i("receiveMsg : ", receiveMsg);

                    reader.close();
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return listjsonParser(receiveMsg);
        }

        public String listjsonParser(String jsonString) {
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


            String[] arraysum = new String[1];
            try {
                JSONArray jarray = new JSONObject(jsonString).getJSONArray("res_list");
                for (int i = 0; i < jarray.length(); i++) {
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

                    a[i] = tran_date;

                    result += a[i] + "\n";

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

    }


    public static class Task3 extends AsyncTask<String, Void, String> {
        private String str, receiveMsg;
        String start = ((MainActivity) MainActivity.context).s_start;
        String end = ((MainActivity) MainActivity.context).s_end;

        @Override
        protected String doInBackground(String... params) {
            Random rand = new Random();
            String numStr = "";

            for(int i=0;i<9;i++) {
                String ran = Integer.toString(rand.nextInt(9));
                numStr += ran;
            }
            String header = "Bearer"+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzY0MDIyIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MDk5OTQzODcsImp0aSI6IjY2MTgzYjg3LTEyNTUtNDcwMi1iMDEwLTY2MzUzNDlmMTc5OCJ9.O6UnAju-wZkbywcvHe0GVlSxTkFuGODpY137DnGMMhw";
            URL url = null;
            try {
                url = new URL("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991663280U"+numStr+"&fintech_use_num=199166328057887381558426&inquiry_type=A&inquiry_base=D&from_date="+start+"&to_date="+end+"&sort_order=D&tran_dtime=20201006025351");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization",header);

                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.i("receiveMsg : ", receiveMsg);

                    reader.close();
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
            String result ="";

            String[] a = new String[100];


            String[] arraysum = new String[1];
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

                    a[i]=tran_type;

                    result += a[i]+"\n";

                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return result;
        }

    }

    public static class Task4 extends AsyncTask<String, Void, String> {
        private String str, receiveMsg;
        String start = ((MainActivity) MainActivity.context).s_start;
        String end = ((MainActivity) MainActivity.context).s_end;

        @Override
        protected String doInBackground(String... params) {
            Random rand = new Random();
            String numStr = "";

            for(int i=0;i<9;i++) {
                String ran = Integer.toString(rand.nextInt(9));
                numStr += ran;
            }
            String header = "Bearer"+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzY0MDIyIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MDk5OTQzODcsImp0aSI6IjY2MTgzYjg3LTEyNTUtNDcwMi1iMDEwLTY2MzUzNDlmMTc5OCJ9.O6UnAju-wZkbywcvHe0GVlSxTkFuGODpY137DnGMMhw";
            URL url = null;
            try {
                url = new URL("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991663280U"+numStr+"&fintech_use_num=199166328057887381558426&inquiry_type=A&inquiry_base=D&from_date="+start+"&to_date="+end+"&sort_order=D&tran_dtime=20201006025351");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization",header);

                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.i("receiveMsg : ", receiveMsg);

                    reader.close();
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
            String result ="";

            String[] a = new String[100];


            String[] arraysum = new String[1];
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

                    a[i]=print_content;

                    result += a[i]+"\n";

                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return result;
        }

    }

    public static class Task5 extends AsyncTask<String, Void, String> {
        private String str, receiveMsg;
        String start = ((MainActivity) MainActivity.context).s_start;
        String end = ((MainActivity) MainActivity.context).s_end;

        @Override
        protected String doInBackground(String... params) {
            Random rand = new Random();
            String numStr = "";

            for(int i=0;i<9;i++) {
                String ran = Integer.toString(rand.nextInt(9));
                numStr += ran;
            }
            String header = "Bearer"+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzY0MDIyIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MDk5OTQzODcsImp0aSI6IjY2MTgzYjg3LTEyNTUtNDcwMi1iMDEwLTY2MzUzNDlmMTc5OCJ9.O6UnAju-wZkbywcvHe0GVlSxTkFuGODpY137DnGMMhw";
            URL url = null;
            try {
                url = new URL("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991663280U"+numStr+"&fintech_use_num=199166328057887381558426&inquiry_type=A&inquiry_base=D&from_date="+start+"&to_date="+end+"&sort_order=D&tran_dtime=20201006025351");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization",header);


                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    Log.i("start date : ", start);
                    Log.i("end date : ", end);
                    receiveMsg = buffer.toString();
                    Log.i("receiveMsg : ", receiveMsg);

                    reader.close();
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
            String result ="";

            String[] a = new String[100];


            String[] arraysum = new String[1];
            try{
                JSONArray jarray = new JSONObject(jsonString).getJSONArray("res_list");
                for(int i=0;i<jarray.length();i++){
                    JSONObject jObject = jarray.getJSONObject(i);

                    tran_date = jObject.optString("tran_date");
                    tran_time = jObject.optString("tran_time");
                    inout_type = jObject.optString("inout_type");
                    tran_type = jObject.optString("tran_type");
                    print_content = jObject.optString("print_content");
                    tran_amt = jObject.optString("tran_amt");
                    after_balance_amt = jObject.optString("after_balance_amt");
                    branch_name = jObject.optString("branch_name");

                    a[i]=tran_amt;

                    result += a[i]+"\n";

                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return result;
        }

    }

    static class Task2 extends AsyncTask<String, Void, String> {
        private String str, receiveMsg;
        String start = ((MainActivity) MainActivity.context).s_start;
        String end = ((MainActivity) MainActivity.context).s_end;

        @Override
        protected String doInBackground(String... params) {
            Random rand = new Random();
            String numStr = "";

            for(int i=0;i<9;i++) {
                String ran = Integer.toString(rand.nextInt(9));
                numStr += ran;
            }
            String header = "Bearer"+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzY0MDIyIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MDk5OTQzODcsImp0aSI6IjY2MTgzYjg3LTEyNTUtNDcwMi1iMDEwLTY2MzUzNDlmMTc5OCJ9.O6UnAju-wZkbywcvHe0GVlSxTkFuGODpY137DnGMMhw";
            URL url = null;
            try {
                url = new URL("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991663280U"+numStr+"&fintech_use_num=199166328057887381558426&inquiry_type=A&inquiry_base=D&from_date="+start+"&to_date="+end+"&sort_order=D&tran_dtime=20201006025351");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization",header);

                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.i("receiveMsg : ", receiveMsg);

                    reader.close();
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
            String result ="";

            String[] a = new String[100];


            String[] arraysum = new String[1];
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

                    a[i]=inout_type;

                    result += a[i]+"\n";

                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return result;
        }

    }
}
