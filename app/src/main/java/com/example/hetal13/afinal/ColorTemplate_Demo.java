package com.example.hetal13.afinal;
import android.content.res.Resources;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hetal13 on 19-02-2017.
 */

public class ColorTemplate_Demo {
    public static final int COLOR_NONE = -1;
    public static final int[] COLORFUL_COLORS = {
            Color.rgb(85,139,47),  Color.rgb(91,200,172),Color.rgb(255, 102, 0),
            Color.rgb(192,202,51), Color.rgb(179, 100, 53),Color.rgb(255,171,64),Color.rgb(255,82,82)
    };
    public static int getHoloBlue() {
        return Color.rgb(51, 181, 229);
    }
    public static List<Integer> createColors(Resources r, int[] colors) {

        List<Integer> result = new ArrayList<Integer>();

        for (int i : colors) {
            result.add(r.getColor(i));
        }

        return result;
    }
    public static List<Integer> createColors(int[] colors) {

        List<Integer> result = new ArrayList<Integer>();

        for (int i : colors) {
            result.add(i);
        }

        return result;
    }
}
