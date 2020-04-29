package com.ricopollo.lnieto.pruebagraphview.Entities;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

public class myAxisValueFormatter implements IAxisValueFormatter {
    private DecimalFormat mFormat;

    public myAxisValueFormatter(){
        mFormat = new DecimalFormat("######.0");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value);
    }
}
