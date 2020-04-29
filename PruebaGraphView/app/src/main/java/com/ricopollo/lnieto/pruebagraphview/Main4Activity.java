package com.ricopollo.lnieto.pruebagraphview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.ricopollo.lnieto.pruebagraphview.Entities.Data4;
import com.ricopollo.lnieto.pruebagraphview.Entities.myValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    private BarChart chart4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        chart4 = (BarChart) findViewById(R.id.chart4);

        final List<Data4> data = new ArrayList<>();

        data.add(new Data4(0f, -124f, "12-29"));
        data.add(new Data4(1f, 124f, "121-29"));
        data.add(new Data4(2f, -104f, "121-29"));
        data.add(new Data4(3f, 94f, "12-219"));
        data.add(new Data4(4f, 124f, "12-219"));
        data.add(new Data4(5f, -154f, "121-29"));
        data.add(new Data4(6f, -120f, "12-219"));

        setData(data);
    }

    private void setData(List<Data4> lista) {
        ArrayList<BarEntry> values = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        int green = Color.rgb(110, 180, 105);
        int red = Color.rgb(210, 90, 40);

        for (int i = 0; i < lista.size(); i++) {
            Data4 data = lista.get(i);
            BarEntry entry = new BarEntry(data.xValue, data.yValue);

            values.add(entry);

            if (data.yValue >= 0) {
                colors.add(red);
            } else {
                colors.add(green);
            }
        }

        BarDataSet set = new BarDataSet(values, "Sensores");
        set.setColors(colors);
        set.setValueTextColors(colors);

        BarData data = new BarData(set);
        data.setValueTextSize(15f);
        data.setValueFormatter(new myValueFormatter());
        data.setBarWidth(0.9f);

        chart4.setData(data);
        chart4.setFitBars(true);
        chart4.invalidate();
        chart4.getDescription().setEnabled(false);
    }
}
