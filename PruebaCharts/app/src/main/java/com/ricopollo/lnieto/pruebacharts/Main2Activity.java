package com.ricopollo.lnieto.pruebacharts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    //implements OnChartGestureListener, OnChartValueSelectedListener {

    private static final String TAG = "Main2Activity";
    private LineChart lchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lchart = (LineChart) findViewById(R.id.linechart);

        //lchart.setOnChartGestureListener(Main2Activity.this);
        //lchart.setOnChartValueSelectedListener(Main2Activity.this);
        lchart.setDragEnabled(true);
        lchart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(1, 60f));
        yValues.add(new Entry(2, 50f));
        yValues.add(new Entry(3, 80f));
        yValues.add(new Entry(4, 30f));
        yValues.add(new Entry(5, 60f));
        yValues.add(new Entry(6, 40f));

        LineDataSet set1 = new LineDataSet(yValues, "DataSet 1");
        set1.setFillAlpha(110);
        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(18f);
        set1.setValueTextColor(Color.BLUE);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        lchart.setData(data);
    }
}
