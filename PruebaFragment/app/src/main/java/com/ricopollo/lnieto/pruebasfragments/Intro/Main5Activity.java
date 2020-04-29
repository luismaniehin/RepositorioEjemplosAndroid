package com.ricopollo.lnieto.pruebasfragments.Intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ricopollo.lnieto.pruebasfragments.R;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Intent i = new Intent(Main5Activity.this, MyInto.class);
        startActivity(i);
    }
}
