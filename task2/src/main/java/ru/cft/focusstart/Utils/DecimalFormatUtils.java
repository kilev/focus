package ru.cft.focusstart.Utils;

import java.text.DecimalFormat;

public class DecimalFormatUtils {

    private static final String format = "#0.00";
    private static final DecimalFormat formatter = new DecimalFormat(format);

    private DecimalFormatUtils() {
    }

    public static String format(double value){
        return formatter.format(value);
    }
}
