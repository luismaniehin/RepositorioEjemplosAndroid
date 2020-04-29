package com.ricopollo.lnieto.ejemplorecyclerview2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ricopollo.lnieto.ejemplorecyclerview2.Ejemplo1.MainActivity;
import com.squareup.leakcanary.RefWatcher;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        new MyAsyncTask().execute(this);
    }

    public class MyAsyncTask extends AsyncTask<Object, String, String>{
        private Context context;

        @Override
        protected String doInBackground(Object... objects) {
            context = (Context) objects[0];

            SingletonSavesContext.getInstance().setContext(context);

            try{
                Thread.sleep(10000);
            } catch (InterruptedException e) {}

            return "result";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        RefWatcher refWatcher = MyApply.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
