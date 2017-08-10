package com.sgp95.santiago.firebasetaskapp.presenter;

import com.sgp95.santiago.firebasetaskapp.api.database.DatabaseHelper;
import com.sgp95.santiago.firebasetaskapp.util.StringUtils;
import com.sgp95.santiago.firebasetaskapp.view.AddTaskView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddTaskPresenterImpl implements AddTaskPresenter {
    private AddTaskView addTaskView;

    private final DatabaseHelper databaseHelper;

    private SimpleDateFormat dateFormat;
    private SimpleDateFormat hourFormat;

    public AddTaskPresenterImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
        dateFormat = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
        hourFormat = new SimpleDateFormat("hh:mm a");
    }

    @Override
    public void userClickedCalendarView(int day, int month, int year) {
        addTaskView.updateDateTextView(StringUtils.dateToDateString(day,month,year));
    }

    @Override
    public void userClickedTimePicker(int hour, int minute) {
        addTaskView.updateHourTextView(StringUtils.dateToHourString(hour,minute));
    }

    @Override
    public void userClickedSendTaskButton(String title, String detail, String date, String hour, String place,String latitude, String longitude) {
        if(!StringUtils.stringsAreNullOrEmpty(title,detail,date,hour,place,latitude,longitude)){
            addTaskView.resetScreen();
            databaseHelper.sendTaskToFirebase(title,detail,date,hour,place,latitude,longitude);
        }else {
            addTaskView.showTaskErrorMessage();
        }
    }

    @Override
    public void userClickedMapButton() {
        addTaskView.startMapActivity();
    }

    @Override
    public void setAlarm(Calendar alarm,String alarmTitle,String alarmDetail) {
        addTaskView.createAlarm(alarm,alarmTitle,alarmDetail);
    }

    @Override
    public void setView(AddTaskView view) {
        this.addTaskView = view;
    }
}
