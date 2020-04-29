package com.ricopollo.lnieto.pruebagraphview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.ricopollo.lnieto.pruebagraphview.Entities.ChartDataAdapter;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {

    private ListView listaSensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        listaSensores = (ListView) findViewById(R.id.listaSensores);

        ArrayList<BarData> lista = new ArrayList<>();

        for (int i = 0; i <= 20; i++) {
            lista.add(mxGenerarData(i + 1));
        }

        ChartDataAdapter adapter = new ChartDataAdapter(getApplicationContext(), lista);
        listaSensores.setAdapter(adapter);
    }

    private BarData mxGenerarData(int count) {
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float value = (float) (Math.random() * count) + 30;
            entries.add(new BarEntry(i, value));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Device " + count);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setBarShadowColor(Color.rgb(203, 203, 203));

        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f);
        return data;
    }
}