package com.example.json_static_yr;

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

public class NewActivity2 {

    public static class MonthTotal extends AsyncTask<String, Void, String> {
        private String str, receiveMsg;
        String select_ym = ((SubActivity2) SubActivity2.context).select_ym;
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
                url = new URL("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991663280U" + numStr + "&fintech_use_num=199166328057887392605371&inquiry_type=A&inquiry_base=D&from_date=" + select_ym+"01" + "&to_date=" + select_ym+"30"+ "&sort_order=D&tran_dtime=20201006025351");

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
            String[] b = new String[100];
            String[] c = new String[100];
            int sum =0;
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
                    b[i] = inout_type;
                    c[i] = tran_amt;


                    if(a[i].contains(select_ym)&&b[i].contains("출금")){
                        sum += Integer.parseInt(c[i]);
                    }
                }
                result =  Integer.toString(sum);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

    }
    public static class MonthFood extends AsyncTask<String, Void, String> {
        private String str, receiveMsg;
        String select_ym = ((SubActivity2) SubActivity2.context).select_ym;
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
                url = new URL("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991663280U" + numStr + "&fintech_use_num=199166328057887392605371&inquiry_type=A&inquiry_base=D&from_date=" + select_ym+"01" + "&to_date=" + select_ym+"30"+ "&sort_order=D&tran_dtime=20201006025351");

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
            String[] b = new String[100];
            String[] c = new String[100];
            int sum =0;
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
                    b[i] = tran_type;
                    c[i] = tran_amt;


                    if(a[i].contains(select_ym)&&b[i].contains("식비")){
                        sum += Integer.parseInt(c[i]);
                    }
                }
                result =  Integer.toString(sum);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

    }
    public static class MonthShop extends AsyncTask<String, Void, String> {
        private String str, receiveMsg;
        String select_ym = ((SubActivity2) SubActivity2.context).select_ym;
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
                url = new URL("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991663280U" + numStr + "&fintech_use_num=199166328057887392605371&inquiry_type=A&inquiry_base=D&from_date=" + select_ym+"01" + "&to_date=" + select_ym+"30"+ "&sort_order=D&tran_dtime=20201006025351");

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
            String[] b = new String[100];
            String[] c = new String[100];
            int sum =0;
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
                    b[i] = tran_type;
                    c[i] = tran_amt;


                    if(a[i].contains(select_ym)&&b[i].contains("쇼핑")){
                        sum += Integer.parseInt(c[i]);
                    }
                }
                result =  Integer.toString(sum);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

    }
    public static class MonthLife extends AsyncTask<String, Void, String> {
        private String str, receiveMsg;
        String select_ym = ((SubActivity2) SubActivity2.context).select_ym;
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
                url = new URL("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991663280U" + numStr + "&fintech_use_num=199166328057887392605371&inquiry_type=A&inquiry_base=D&from_date=" + select_ym+"01" + "&to_date=" + select_ym+"30"+ "&sort_order=D&tran_dtime=20201006025351");

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
            String[] b = new String[100];
            String[] c = new String[100];
            int sum =0;
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
                    b[i] = tran_type;
                    c[i] = tran_amt;


                    if(a[i].contains(select_ym)&&b[i].contains("생활")){
                        sum += Integer.parseInt(c[i]);
                    }
                }
                result =  Integer.toString(sum);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

    }public static class MonthCulture extends AsyncTask<String, Void, String> {
        private String str, receiveMsg;
        String select_ym = ((SubActivity2) SubActivity2.context).select_ym;
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
                url = new URL("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991663280U" + numStr + "&fintech_use_num=199166328057887392605371&inquiry_type=A&inquiry_base=D&from_date=" + select_ym+"01" + "&to_date=" + select_ym+"30"+ "&sort_order=D&tran_dtime=20201006025351");

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
            String[] b = new String[100];
            String[] c = new String[100];
            int sum =0;
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
                    b[i] = tran_type;
                    c[i] = tran_amt;


                    if(a[i].contains(select_ym)&&b[i].contains("문화/여가")){
                        sum += Integer.parseInt(c[i]);
                    }
                }
                result =  Integer.toString(sum);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

    }
    public static class MonthTraffic extends AsyncTask<String, Void, String> {
        private String str, receiveMsg;
        String select_ym = ((SubActivity2) SubActivity2.context).select_ym;
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
                url = new URL("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991663280U" + numStr + "&fintech_use_num=199166328057887392605371&inquiry_type=A&inquiry_base=D&from_date=" + select_ym+"01" + "&to_date=" + select_ym+"30"+ "&sort_order=D&tran_dtime=20201006025351");

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
            String[] b = new String[100];
            String[] c = new String[100];
            int sum =0;
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
                    b[i] = tran_type;
                    c[i] = tran_amt;


                    if(a[i].contains(select_ym)&&b[i].contains("교통")){
                        sum += Integer.parseInt(c[i]);
                    }
                }
                result =  Integer.toString(sum);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

    }
    public static class MonthOther extends AsyncTask<String, Void, String> {
        private String str, receiveMsg;
        String select_ym = ((SubActivity2) SubActivity2.context).select_ym;
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
                url = new URL("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?bank_tran_id=T991663280U" + numStr + "&fintech_use_num=199166328057887392605371&inquiry_type=A&inquiry_base=D&from_date=" + select_ym+"01" + "&to_date=" + select_ym+"30"+ "&sort_order=D&tran_dtime=20201006025351");

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
            String[] b = new String[100];
            String[] c = new String[100];
            int sum =0;
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
                    b[i] = tran_type;
                    c[i] = tran_amt;


                    if(a[i].contains(select_ym)&&b[i].contains("기타")){
                        sum += Integer.parseInt(c[i]);
                    }
                }
                result =  Integer.toString(sum);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

    }

}
