package com.ricopollo.lnieto.pruebagraphview;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

public class Main6Activity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "Main6Activity";

    private SensorManager sensorManager;
    private Sensor sensor;

    private LineChart chart6;
    private Thread thread;
    private boolean plotData = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
        }

        chart6 = (LineChart) findViewById(R.id.chart6);

        chart6.getDescription().setEnabled(true);
        chart6.getDescription().setText("Real time sensor data plot");

        chart6.setTouchEnabled(false);
        chart6.setDragEnabled(false);
        chart6.setScaleEnabled(false);
        chart6.setDrawGridBackground(false);
        chart6.setPinchZoom(false);
        chart6.setBackgroundColor(Color.WHITE);

        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);
        chart6.setData(data);

        Legend legend = chart6.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextColor(Color.WHITE);

        XAxis xl = chart6.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(true);
        xl.setAvoidFirstLastClipping(false);
        xl.setEnabled(true);

        YAxis leftAxis = chart6.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMaximum(10f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = chart6.getAxisRight();
        rightAxis.setEnabled(false);

        chart6.getAxisLeft().setDrawGridLines(false);
        chart6.getXAxis().setDrawGridLines(false);
        chart6.setDrawBorders(false);

        mxStartPlot();
    }

    private void mxStartPlot() {
        if (thread != null) {
            thread.interrupt();
        }

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    plotData = true;

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }

    private void mxAddEntry(SensorEvent event) {
        LineData data = chart6.getData();

        if (data != null) {
            ILineDataSet set = data.getDataSetByIndex(0);

            if (set == null) {
                set = mxCreateSet();
                data.addDataSet(set);
            }

            data.addEntry(new Entry(set.getEntryCount(), event.values[0] + 5), 0);
            data.notifyDataChanged();

            chart6.notifyDataSetChanged();
            chart6.setVisibleXRangeMaximum(150);
            //chart6.setMaxVisibleValueCount(150);
            chart6.moveViewToX(data.getEntryCount());
        }
    }

    private LineDataSet mxCreateSet() {
        LineDataSet set = new LineDataSet(null, "Dynamic data");

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setLineWidth(4f);
        set.setColor(Color.BLUE);
        set.setHighlightEnabled(false);
        set.setDrawValues(true);
        set.setDrawCircles(false);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setCubicIntensity(0.2f);

        return set;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (plotData) {
            mxAddEntry(event);
            plotData = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(thread!=null){
            thread.interrupt();
        }

        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(Main6Activity.this);
        thread.interrupt();
        super.onDestroy();
    }
}
