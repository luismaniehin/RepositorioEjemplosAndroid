package com.ricopollo.lnieto.pruebaswifi;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;
    Intent intentBluetoothON;
    IntentFilter intentFilter;

    ArrayAdapter<String> adapter;
    ArrayList<String> listaDevicesBT = new ArrayList<String>();

    ListView listaDevices;
    Button btnON, btnOFF, btnScan;

    int REQUEST_ENABLE_BLUETOOTH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        btnON = (Button) findViewById(R.id.btnON);
        btnOFF = (Button) findViewById(R.id.btnOFF);
        btnScan = (Button) findViewById(R.id.btnScan);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        intentBluetoothON = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);

        mxBluetoothON();
        mxBluetoothOFF();

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetoothAdapter.startDiscovery();

                adapter = new ArrayAdapter<String>(BluetoothActivity.this,
                        android.R.layout.simple_list_item_1, listaDevicesBT);
                listaDevices.setAdapter(adapter);
            }
        });

        registerReceiver(receiverBluetooth, intentFilter);
    }

    private void mxBluetoothON(){
        btnON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bluetoothAdapter != null){
                    if(!bluetoothAdapter.isEnabled()){
                        intentBluetoothON = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(intentBluetoothON, REQUEST_ENABLE_BLUETOOTH);

                        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
                        String[] names = new String[devices.size()];
                        int i = 0;

                        if(devices.size() > 0){
                            for(BluetoothDevice device : devices){
                                names[i] = device.getName() + " - " + device.getAddress();
                                i++;
                            }

                            adapter = new ArrayAdapter<String>(BluetoothActivity.this,
                                    android.R.layout.simple_list_item_1, names);
                            listaDevices.setAdapter(adapter);
                        }
                    }
                } else {
                    Toast.makeText(BluetoothActivity.this, "Device doesn't support bluetooth", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void mxBluetoothOFF(){
        btnOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bluetoothAdapter.isEnabled()){
                    bluetoothAdapter.disable();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_ENABLE_BLUETOOTH){
            if(resultCode == RESULT_OK){
                Toast.makeText(BluetoothActivity.this, "Bluetooth ENABLE", Toast.LENGTH_SHORT).show();
            } else if(resultCode == RESULT_CANCELED){
                Toast.makeText(BluetoothActivity.this, "Bluetooth Enable Canceled", Toast.LENGTH_SHORT).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    BroadcastReceiver receiverBluetooth = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                listaDevicesBT.add(device.getName());
                adapter.notifyDataSetChanged();
            }
        }
    };

}
