package com.ricopollo.lnieto.pruebagraphview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.ricopollo.lnieto.pruebagraphview.Entities.myValueFormatter;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private BarChart chart3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        chart3 = (BarChart) findViewById(R.id.chart3);
        chart3.setMaxVisibleValueCount(40);
        setData(10);
    }

    private void setData(int count){
        ArrayList<BarEntry> values = new ArrayList<>();

        for(int i=0;i<count;i++){
            float value1 = (float) (Math.random()*count)+20;
            float value2 = (float) (Math.random()*count)+20;
            float value3 = (float) (Math.random()*count)+20;

            values.add(new BarEntry(i, new float[]{value1, value2, value3}));
        }

        BarDataSet set = new BarDataSet(values, "Sensores");
        set.setDrawIcons(false);
        set.setStackLabels(new String[]{"Ciclicos", "Puntuales", "Otros"});
        set.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(set);
        data.setValueFormatter(new myValueFormatter());

        chart3.setData(data);
        chart3.setFitBars(true);
        chart3.invalidate();
        chart3.getDescription().setEnabled(false);
    }
}
