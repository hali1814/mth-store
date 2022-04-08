package com.example.mthshop.fortmat;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FortMartData {
    public static String fortMartTypeDoubleToMoney(double data) {
        DecimalFormat dfGerman = new DecimalFormat("#,###.##",
                new DecimalFormatSymbols(Locale.GERMAN));
        return dfGerman.format(data);
    }
}
