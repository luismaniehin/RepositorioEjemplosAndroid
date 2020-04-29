package com.ricopollo.lnieto.pruebasfragments;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.ricopollo.lnieto.pruebasfragments.Ejemplo3.Main4Activity;
import com.ricopollo.lnieto.pruebasfragments.Intro.Main5Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layoutPager = findViewById(R.id.layoutPager);
        Button btnOpenAct1 = findViewById(R.id.btnOpen);
        Button btnOpenAct3 = findViewById(R.id.btnOpen3);
        Button btnOpenAct4 = findViewById(R.id.btnOpen4);
        Button btnOpenAct5 = findViewById(R.id.btnOpen5);

        ViewPager viewPager = findViewById(R.id.viewPager);
        if (viewPager != null) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
        }

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        btnOpenAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                //finish();
            }
        });

        btnOpenAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);
            }
        });

        btnOpenAct4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                startActivity(intent);
            }
        });

        btnOpenAct5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main5Activity.class);
                startActivity(intent);
            }
        });

        //layoutPager.setBackgroundColor(Color.RED);
    }

}











