package com.ricopollo.lnieto.pruebabt;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button  btnON, btnOFF, btnAct1, btnAct2, btnAct3, btnAct4,
            btnAct5, btnAct6, btnAct7, btnAct8, btnAct9;

    BluetoothAdapter bluetoothAdapter;
    Intent intentBluetoothON;
    int REQUEST_CODE_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnON = (Button) findViewById(R.id.btnON);
        btnOFF = (Button) findViewById(R.id.btnOFF);
        btnAct1 = (Button) findViewById(R.id.btnAct1);
        btnAct2 = (Button) findViewById(R.id.btnAct2);
        btnAct3 = (Button) findViewById(R.id.btnAct3);
        btnAct4 = (Button) findViewById(R.id.btnAct4);
        btnAct5 = (Button) findViewById(R.id.btnAct5);
        btnAct6 = (Button) findViewById(R.id.btnAct6);
        btnAct7 = (Button) findViewById(R.id.btnAct7);
        btnAct8 = (Button) findViewById(R.id.btnAct8);
        btnAct9 = (Button) findViewById(R.id.btnAct9);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        intentBluetoothON = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

        btnON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter == null) {
                    Toast.makeText(MainActivity.this, "BT not supported", Toast.LENGTH_SHORT).show();
                } else {
                    if (!bluetoothAdapter.isEnabled()) {
                        startActivityForResult(intentBluetoothON, REQUEST_CODE_ENABLE_BT);
                    }
                }
            }
        });

        btnOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter.isEnabled()) {
                    bluetoothAdapter.disable();
                    Toast.makeText(MainActivity.this, "BT disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mxControlarEventos();
    }

    private void mxControlarEventos(){
        btnAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        btnAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);
            }
        });

        btnAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                startActivity(intent);
            }
        });

        btnAct4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main5Activity.class);
                startActivity(intent);
            }
        });

        btnAct5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main6Activity.class);
                startActivity(intent);
            }
        });

        btnAct6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main7Activity.class);
                startActivity(intent);
            }
        });

        btnAct7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        btnAct8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        btnAct9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainActivity.this, "BT enable", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "BT enable Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

}