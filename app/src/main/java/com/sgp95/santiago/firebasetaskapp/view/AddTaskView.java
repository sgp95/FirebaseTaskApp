package com.sgp95.santiago.firebasetaskapp.view;

import java.util.Calendar;

public interface AddTaskView {

    void updateDateTextView(String date);

    void updateHourTextView(String hour);

    void resetScreen();

    void showTaskErrorMessage();

    void startMapActivity();

    void createAlarm(
            Calendar alarm,
            String alarmTitle,
            String alarmDetail
    );
}
