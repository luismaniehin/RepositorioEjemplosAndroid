package com.ricopollo.lnieto.pruebacharts;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ChartDataAdapter extends ArrayAdapter<BarData> {

    public ChartDataAdapter(Context context, List<BarData> list) {
        super(context, 0, list);
    }

    @NonNull
    @android.support.annotation.NonNull
    @Override
    public View getView(int position, @Nullable @android.support.annotation.Nullable View convertView,
                        @NonNull @android.support.annotation.NonNull ViewGroup parent) {

        BarData data = getItem(position);
        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_barchart, null);
            holder.chart = (BarChart) convertView.findViewById(R.id.chart);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        data.setValueTextColor(Color.BLACK);
        holder.chart.getDescription().setEnabled(false);
        holder.chart.setDrawGridBackground(false);

        XAxis xAxis = holder.chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = holder.chart.getAxisLeft();
        leftAxis.setLabelCount(5, false);
        leftAxis.setSpaceTop(15f);

        YAxis rightAxis = holder.chart.getAxisRight();
        rightAxis.setLabelCount(5, false);
        rightAxis.setSpaceTop(15f);

        holder.chart.setData(data);
        holder.chart.setFitBars(true);
        holder.chart.animateY(500);

        return convertView;
    }
}
