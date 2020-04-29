package com.ricopollo.lnieto.pruebagraphview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private BarChart chart2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        chart2 = (BarChart) findViewById(R.id.chart2);
        chart2.getDescription().setEnabled(false);

        setData(20);

        chart2.setFitBars(true);
        chart2.animateX(500);
    }

    private void setData(int count){
        ArrayList<BarEntry> values = new ArrayList<>();

        for(int i=0;i<count;i++){
            float value = (float) (Math.random()*100);
            values.add(new BarEntry(i, value));
        }

        BarDataSet set = new BarDataSet(values, "Devices");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setDrawValues(true);

        BarData data = new BarData(set);
        chart2.setData(data);
        chart2.invalidate();
        chart2.animateY(500);
    }
}
