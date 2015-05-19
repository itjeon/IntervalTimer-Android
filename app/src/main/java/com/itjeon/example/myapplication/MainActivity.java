package com.itjeon.example.myapplication;

import android.app.Activity;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private MessageHandler mHandler;
    private boolean isPlay;
    private CountDownTimer countDownTimer;

    private TextView tvSet;
    private TextView tvMinute;
    private TextView tvSecond;
    private TextView tvSecPer100;
    private Button btnStart;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new MessageHandler();

        tvSet = (TextView)findViewById(R.id.valueSet);
        tvMinute = (TextView)findViewById(R.id.valueMinute);
        tvSecond = (TextView)findViewById(R.id.valueSecond);
        tvSecPer100 = (TextView)findViewById(R.id.valueSecondPer100);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPlay = true;
                countDownStart(1 * 60 * 1000, 100);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPlay = false;
                countDownStop();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void countDownStart(long millisInFuture, long countDownInterval){

        countDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {
            int cnt;
            long min;
            long sec;
            long secPer100;

            public void onTick(long millisUntilFinished) {
                long _min = millisUntilFinished / (60*1000);
                long _sec = (millisUntilFinished % (60 * 1000)) / 1000;
                secPer100 = (millisUntilFinished % 1000) / 100;

                if( min != _min){
                    min = _min;
                    tvMinute.setText(min + "");
                }

                if( sec != _sec){
                    sec = _sec;
                    tvSecond.setText(sec + "");
                }

                tvSecPer100.setText(secPer100+"");
            }

            public void onFinish() {
                isPlay = !isPlay;
                if(!isPlay){
                    countDownStart(30*1000, 100);
                }else{
                    countDownStart(1*60*1000, 100);
                }
            }
        }.start();

    }

    public void countDownStop(){
        isPlay = false;
        tvMinute.setText("00");
        tvSecond.setText("00");
        tvSecPer100.setText("00");

        if(countDownTimer != null)
            countDownTimer.cancel();
    }

    // MessageHandler
    class MessageHandler extends Handler {

        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
        }
    }
}
