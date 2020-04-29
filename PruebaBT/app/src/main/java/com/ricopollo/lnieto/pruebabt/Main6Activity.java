package com.ricopollo.lnieto.pruebabt;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main6Activity extends AppCompatActivity {

    TextView txcScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        txcScan = (TextView) findViewById(R.id.txcScan);

        ThreadScan threadScan = new ThreadScan();
        threadScan.start();
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            txcScan.setText(String.valueOf(msg.arg1));
            return false;
        }
    });

    private class ThreadScan extends Thread {
        @Override
        public void run() {
            for(int i=0; i< 50; i++){
                //txcScan.setText(String.valueOf(i));   error

                Message message = Message.obtain();
                message.arg1 = i;

                handler.sendMessage(message);

                try{
                    sleep(500);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
