package com.ricopollo.lnieto.pruebagraphview.Entities;

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
import com.ricopollo.lnieto.pruebagraphview.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ChartDataAdapter extends ArrayAdapter<BarData> {

    public ChartDataAdapter(Context context, List<BarData> objects){
        super(context, 0, objects);
    }

    @NonNull
    @android.support.annotation.NonNull
    @Override
    public View getView(int position, @Nullable @android.support.annotation.Nullable View convertView, @NonNull @android.support.annotation.NonNull ViewGroup parent) {
        BarData data = getItem(position);
        ViewHolder holder = null;
        LayoutInflater inflater;

        if(convertView == null){
            holder = new ViewHolder();

            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_barchart, null);
            holder.chart = (BarChart) convertView.findViewById(R.id.chart5);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        data.setValueTextColor(Color.BLACK);
        holder.chart.getDescription().setEnabled(false);
        holder.chart.setDrawGridBackground(false);
        holder.chart.getAxisRight().setEnabled(false);

        XAxis xAxis = holder.chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = holder.chart.getAxisLeft();
        leftAxis.setLabelCount(5, false);
        leftAxis.setSpaceTop(15f);

        holder.chart.setData(data);
        holder.chart.setFitBars(true);
        holder.chart.animateY(500);

        return convertView;
    }
}
