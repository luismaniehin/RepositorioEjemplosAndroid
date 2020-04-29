package com.ricopollo.lnieto.pruebabt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Set;

public class Main2Activity extends AppCompatActivity {

    Button btnScan;
    ListView listaDev;

    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnScan = (Button) findViewById(R.id.btnScan);
        listaDev = (ListView) findViewById(R.id.listaDev);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
                String[] names = new String[devices.size()];
                int i = 0;

                if(devices.size() > 0){
                    for(BluetoothDevice dev : devices){
                        names[i] = dev.getName();
                        i++;
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, names);
                    listaDev.setAdapter(arrayAdapter);
                }
            }
        });
    }
}
