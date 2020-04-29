package com.ricopollo.lnieto.pruebaswifi;

import android.Manifest;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.ricopollo.lnieto.pruebaswifi.Adpaters.ListAdapter;

import java.util.List;

public class WifiActivity extends AppCompatActivity {

    WifiInfo wifiInfo;
    WifiManager wifiManager;
    WifiReceiver wifiReceiver;

    ListAdapter listAdapter;
    ListView listaRedes;
    List wifiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        listaRedes = (ListView) findViewById(R.id.listaRedes);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
        wifiReceiver = new WifiReceiver();

        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else {
            this.mxScanWifiList();
        }
    }

    private void mxScanWifiList() {
        wifiManager.startScan();
        wifiList = wifiManager.getScanResults();

        this.mxSetAdapter();
    }

    private void mxSetAdapter() {
        listAdapter = new ListAdapter(getApplicationContext(), wifiList);
        listaRedes.setAdapter(listAdapter);
    }


    @Override
    protected void onResume() {
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(wifiReceiver);
        super.onPause();
    }

}