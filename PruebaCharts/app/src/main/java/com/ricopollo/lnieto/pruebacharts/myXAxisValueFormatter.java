package com.ricopollo.lnieto.pruebacharts;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class myXAxisValueFormatter implements IAxisValueFormatter {
    private String[] values;

    public myXAxisValueFormatter(String[] values){
        this.values = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return values[(int) value];
    }
}
