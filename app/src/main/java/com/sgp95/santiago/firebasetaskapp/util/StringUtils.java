package com.sgp95.santiago.firebasetaskapp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StringUtils {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
    private static SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm a");

    public static boolean stringsAreNullOrEmpty(String... strings){
        for(String s : strings){
            if (s == null || s.isEmpty() || s.trim().isEmpty()){
                return true;
            }
        }
        return false;
    }

    public static String dateToDateString(int day, int month, int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.YEAR,year);
        String taskDate = dateFormat.format(calendar.getTime());
        return taskDate;
    }

    public static String dateToHourString(int hour, int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        String taskHour = hourFormat.format(calendar.getTime());
        return taskHour;
    }
}
