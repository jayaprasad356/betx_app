package com.ayu.bigbillion;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity2 extends AppCompatActivity {

    TextView tvtime;

    public static final String TIME_SERVER = "time-a.nist.gov";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        //new GetNetworkTimeTask().execute();
        tvtime = findViewById(R.id.tvtime);

    }
//
//    class GetNetworkTimeTask extends AsyncTask<Void, Void, Date> {
//
//        @Override
//        protected Date doInBackground(Void... voids) {
//            NTPUDPClient timeClient = new NTPUDPClient();
//            try {
//                InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
//                TimeInfo timeInfo = timeClient.getTime(inetAddress);
//                long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();   //server time
//                return new Date(returnTime);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Date time) {
//            super.onPostExecute(time);
//            if (time != null) {
//                tvtime.setText(time.toString());
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    gets(time);
//                }
//                Log.e("getCurrentNetworkTime", "Time from " + TIME_SERVER + ": " + time);
//                Log.e("Local time", "Local time");
//                Log.e("Local time", "Current time: " + new Date(System.currentTimeMillis()));
//            } else {
//                Log.e("getCurrentNetworkTime", "Failed to get network time");
//            }
//        }
//    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void gets(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH) + 1; // Month is 0-based in Calendar
        int year = calendar.get(Calendar.YEAR);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String time = timeFormat.format(date);
        System.out.println("Time: " + time);
        System.out.println("Date: " + day);
        System.out.println("Month: " + month);
        System.out.println("Year: " + year);
        Toast.makeText(this, time+day+month+year, Toast.LENGTH_SHORT).show();




    }
}