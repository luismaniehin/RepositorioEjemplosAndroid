package com.ricopollo.lnieto.pruebagraphview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LineChart chart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chart1 = (LineChart) findViewById(R.id.chart1);
        setData(40, 60);
        chart1.animateX(1000);
    }

    private void setData(int count, int range) {
        ArrayList<Entry> yValues1 = new ArrayList<>();
        ArrayList<Entry> yValues2 = new ArrayList<>();
        ArrayList<Entry> yValues3 = new ArrayList<>();
        LineDataSet set1, set2, set3;

        for (int i = 0; i < count; i++) {
            float value = (float) (Math.random() * range) + 200;
            yValues1.add(new Entry(i, value));

            value = (float) (Math.random() * range) + 120;
            yValues2.add(new Entry(i, value));

            value = (float) (Math.random() * range) + 50;
            yValues3.add(new Entry(i, value));
        }

        set1 = new LineDataSet(yValues1, "Sensor1");
        set2 = new LineDataSet(yValues2, "Sensor2");
        set3 = new LineDataSet(yValues3, "Sensor3");

        set1.setColor(Color.RED);
        set2.setColor(Color.BLUE);
        set3.setColor(Color.GREEN);

        set1.setLineWidth(3f);
        set2.setLineWidth(3f);
        set3.setLineWidth(3f);

        LineData data = new LineData(set1, set2, set3);
        chart1.setData(data);
    }
}
