package com.ricopollo.lnieto.pruebagraphview.Entities;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class myValueFormatter implements IValueFormatter {
    private DecimalFormat mFormat;

    public myValueFormatter(){
        mFormat = new DecimalFormat("######.0");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormat.format(value) + "$";
    }
}
