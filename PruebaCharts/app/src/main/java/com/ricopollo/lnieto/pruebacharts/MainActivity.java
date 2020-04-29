package com.ricopollo.lnieto.pruebacharts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView1;
    ArrayList<BarData> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView1 = (ListView) findViewById(R.id.listView1);
        lista = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            lista.add(generateData(i * 1));
        }

        ChartDataAdapter chartDataAdapter = new ChartDataAdapter(getApplicationContext(), lista);
        listView1.setAdapter(chartDataAdapter);
    }

    private BarData generateData(int count) {
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry(i, (float) (Math.random() * 70) + 30));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Nuevo dataset" + count);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setBarShadowColor(Color.rgb(203, 203, 203));

        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f);
        return data;
    }
}
