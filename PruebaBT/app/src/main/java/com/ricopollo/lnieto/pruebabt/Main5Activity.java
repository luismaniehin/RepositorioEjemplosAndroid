package com.ricopollo.lnieto.pruebabt;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {

    Button btnScan;
    TextView txcScan;

    IntentFilter scanFilterIntent = new IntentFilter(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        btnScan = (Button) findViewById(R.id.btnScan);
        txcScan = (TextView) findViewById(R.id.txcScan);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 8);
                startActivity(discoverableIntent);
            }
        });

        registerReceiver(scanReceiver, scanFilterIntent);
    }

    BroadcastReceiver scanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)) {
                int modeValue = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, BluetoothAdapter.ERROR);

                if (modeValue == BluetoothAdapter.SCAN_MODE_CONNECTABLE) {
                    txcScan.setText("El dispositivo no está en modo visible, pero puede recibir conexiones");
                } else if (modeValue == BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
                    txcScan.setText("El dispositivo está en modo visible");
                } else if (modeValue == BluetoothAdapter.SCAN_MODE_NONE) {
                    txcScan.setText("Ninguna conexión disponible, no visible");
                } else {
                    txcScan.setText("Error");
                }
            }
        }
    };
}