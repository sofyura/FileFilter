package com.example.filefilter.core;

public class DataClassifier {
    public static boolean isInteger(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFloat(String s) {
        try {
            double d = Double.parseDouble(s);
            return Double.isFinite(d);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}