package com.ricopollo.lnieto.pruebaswifi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BarcodeActivity extends AppCompatActivity {

    private Button btnCodigoQR, btnCodigoBar;

    private static final String SCAN = "com.google.zxing.client.android.SCAN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        btnCodigoQR = (Button) findViewById(R.id.btnCodigoQR);
        btnCodigoBar = (Button) findViewById(R.id.btnCodigoBar);

        btnCodigoQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mxScanQR();
            }
        });

        btnCodigoBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mxScanBar();
            }
        });
    }

    private void mxScanQR(){
        try{
            Intent intent = new Intent(SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException e){
            mxMostrarDialogo(BarcodeActivity.this, "No Scanner Found",
                    "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    private void mxScanBar(){
        try{
            Intent intent = new Intent(SCAN);
            intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException e){

        }
    }

    private Dialog mxMostrarDialogo(final Activity activity, CharSequence title,
                                    CharSequence message, CharSequence Yes, CharSequence No){
        AlertDialog.Builder download = new AlertDialog.Builder(activity);

        download.setTitle(title);
        download.setMessage(message);

        download.setPositiveButton(Yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");

                try{
                    //activity.startActivity();
                } catch (ActivityNotFoundException e){

                }
            }
        });

        download.setNegativeButton(No, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return download.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == 0){
            if(resultCode == RESULT_OK){
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                Toast.makeText(this, "Content" + contents + "Format:" + format, Toast.LENGTH_LONG).show();
            }
        }

        //super.onActivityResult(requestCode, resultCode, intent);
    }
}
