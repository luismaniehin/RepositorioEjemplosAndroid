package com.ricopollo.lnieto.pruebacharts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = "Main3Activity";
    private LineChart lchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lchart = (LineChart) findViewById(R.id.linechart);

        //lchart.setOnChartGestureListener(Main2Activity.this);
        //lchart.setOnChartValueSelectedListener(Main2Activity.this);
        lchart.setDragEnabled(true);
        lchart.setScaleEnabled(false);

        //Limites
        LimitLine upper_limit = new LimitLine(65f, "Upper");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(15f);

        LimitLine lower_limit = new LimitLine(35f, "Lower");
        lower_limit.setLineWidth(2f);
        lower_limit.enableDashedLine(10f, 10f, 10f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(15f);

        //vinculamos límites
        YAxis leftAxis = lchart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaximum(90f);
        leftAxis.setAxisMinimum(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawLimitLinesBehindData(true);

        //ocultamos rightAxis
        lchart.getAxisRight().setEnabled(false);

        //Valores del gráfico de lineas
        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(1, 60f));
        yValues.add(new Entry(2, 50f));
        yValues.add(new Entry(3, 80f));
        yValues.add(new Entry(4, 30f));
        yValues.add(new Entry(5, 60f));
        yValues.add(new Entry(6, 40f));

        //Características del gráfico de lineas
        LineDataSet set1 = new LineDataSet(yValues, "DataSet 1");
        set1.setFillAlpha(110);
        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(18f);
        set1.setValueTextColor(Color.BLUE);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        //asignamos los datos al gráfico
        LineData data = new LineData(dataSets);
        lchart.setData(data);

        //Etiquetas de columnas
        String[] values = {"Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"};
        myXAxisValueFormatter formatter = new myXAxisValueFormatter(values);

        XAxis xAxis = lchart.getXAxis();
        xAxis.setValueFormatter(formatter);
        //xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
    }

}
