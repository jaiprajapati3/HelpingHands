package com.example.hetal13.afinal;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.PercentFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Created by Hetal13 on 21-02-2017.
 */

public class DecimalRemover extends PercentFormatter {
    protected DecimalFormat mFormat;

    public DecimalRemover(DecimalFormat format) {
        this.mFormat = format;
    }


    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        if(value < 10) return "";
        return mFormat.format(value) + " %";
    }
}