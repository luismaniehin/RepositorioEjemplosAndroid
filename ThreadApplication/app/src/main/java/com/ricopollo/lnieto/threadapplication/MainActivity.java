package com.ricopollo.lnieto.threadapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mitexto;
    private Button btnabrir;

    Handler miHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mitexto.setText("Botón presionado con THREAD");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mitexto = (TextView) findViewById(R.id.mitexto);
        btnabrir = (Button) findViewById(R.id.btnAbrir);

        btnabrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intento);
            }
        });
    }

    /*public void mibotonClick(View view) {
        long endTime = System.currentTimeMillis() + 200*1000;

        while(System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception e) { }
            }
        }
        mitexto.setText("Botón presionado!");
    }*/

    public void mibotonClick(View view) {

        Runnable miRunnable = new Runnable() {
            @Override
            public void run() {
                long endTime = System.currentTimeMillis() + 10*1000;

                while(System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (Exception e) { }
                    }
                }
                miHandler.sendEmptyMessage(0);
            }
        };

        Thread miThread = new Thread(miRunnable);
        miThread.start();
    }
}
