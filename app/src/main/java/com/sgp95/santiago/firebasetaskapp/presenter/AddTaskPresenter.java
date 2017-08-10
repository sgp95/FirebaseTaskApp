package com.sgp95.santiago.firebasetaskapp.presenter;

import com.sgp95.santiago.firebasetaskapp.view.AddTaskView;

import java.util.Calendar;

public interface AddTaskPresenter extends Presenter<AddTaskView> {

    void userClickedCalendarView(
            int day,
            int month,
            int year
    );

    void userClickedTimePicker(
            int hour,
            int minute
    );

    void userClickedSendTaskButton(
            String title,
            String detail,
            String date,
            String hour,
            String place,
            String latitude,
            String longitude
    );

    void userClickedMapButton();

    void setAlarm(
            Calendar alarm,
            String alarmTitle,
            String alarmDetail
    );
}
