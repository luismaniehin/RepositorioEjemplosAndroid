package com.ricopollo.lnieto.pruebawifidirect;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

public class WifiDirectBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel wifiChannel;
    private MainActivity activity;

    public WifiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel, MainActivity act){
        wifiP2pManager = manager;
        wifiChannel = channel;
        activity = act;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        switch (action){
            case WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION:
                int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);

                if(state == WifiP2pManager.WIFI_P2P_STATE_ENABLED){
                    Toast.makeText(context, "Wifi Direct ON", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(context, "Wifi Direct OFF", Toast.LENGTH_SHORT).show();
                }
                break;
            case WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION:
                if(wifiP2pManager != null){
                    wifiP2pManager.requestPeers(wifiChannel, activity.peerListListener);
                }
                break;
            case WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION:
                if(wifiP2pManager == null)
                    return;

                NetworkInfo networkInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

                if(networkInfo.isConnected()){
                    wifiP2pManager.requestConnectionInfo(wifiChannel, activity.connectionInfoListener);
                } else {
                    activity.lblConnectionStatus.setText("Device disconected");
                }
                break;
            case WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION:
                break;
        }
    }
}
