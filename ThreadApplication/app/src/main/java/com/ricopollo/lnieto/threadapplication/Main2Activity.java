package com.ricopollo.lnieto.threadapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    private TextView mitexto;

    Handler miHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle miBundle = msg.getData();
            String miString = miBundle.getString("myKey");
            mitexto.setText(miString);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mitexto = (TextView) findViewById(R.id.mitexto);
    }

    public void mibotonClick(View view) {

        Runnable miRunnable = new Runnable() {
            @Override
            public void run() {
                Message msg = miHandler.obtainMessage();
                Bundle miBundle = new Bundle();
                SimpleDateFormat miDateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US);
                String miDateString = miDateFormat.format(new Date());

                miBundle.putString("myKey", miDateString);

                msg.setData(miBundle);
                miHandler.sendMessage(msg);
            }
        };

        Thread miThread = new Thread(miRunnable);
        miThread.start();
        //miThread.stop();
    }
}
