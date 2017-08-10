package com.sgp95.santiago.firebasetaskapp.ui.add;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sgp95.santiago.firebasetaskapp.App;
import com.sgp95.santiago.firebasetaskapp.R;
import com.sgp95.santiago.firebasetaskapp.presenter.AddTaskPresenter;
import com.sgp95.santiago.firebasetaskapp.presenter.AddTaskPresenterImpl;
import com.sgp95.santiago.firebasetaskapp.ui.add.receiver.AlarmService;
import com.sgp95.santiago.firebasetaskapp.ui.map.MapsActivity;
import com.sgp95.santiago.firebasetaskapp.view.AddTaskView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTaskActivity extends AppCompatActivity implements AddTaskView, View.OnClickListener {
    private AddTaskPresenter addTaskPresenter;

    public static final int MAP_ACTIVITY_REQUEST = 31;
    public static final int ALARM_REQUEST_CODE = 11;
    public static final int HOUR_MILISECS = 3600000;

    private String latitude,longitude;
    private Calendar alarmDate;

    @BindView(R.id.addTaskTitle)
    EditText edtTaskTitle;

    @BindView(R.id.addTaskDetail)
    EditText edtTaskDetail;

    @BindView(R.id.addTaskPlace)
    EditText edtTaskPlace;

    @BindView(R.id.addTaskDateText)
    TextView txtTaskDate;

    @BindView(R.id.addTaskHourText)
    TextView txtTaskHour;

    @BindView(R.id.txtPlaceIsSeleceted)
    TextView txtPlaceIsSelected;

    @BindView(R.id.addTaskCalendarView)
    CalendarView calendarView;

    @BindView(R.id.addTaskTimePicker)
    TimePicker timePicker;

    @BindView(R.id.addTaskSendButton)
    Button btnSendTask;

    @BindView(R.id.addTaskMapButton)
    ImageButton btnOpenMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);
        ButterKnife.bind(this);
        initPresenter();
        initUi();

    }

    private void initPresenter(){
        addTaskPresenter = new AddTaskPresenterImpl(App.get().getDatabaseHelper());
        addTaskPresenter.setView(this);
    }

    private void initUi(){
        alarmDate = Calendar.getInstance();
        btnSendTask.setOnClickListener(this);
        btnOpenMap.setOnClickListener(this);
        calendarView.setOnDateChangeListener(provideDateChangedListener());
        timePicker.setOnTimeChangedListener(provideTimeChangedListener());
    }

    private CalendarView.OnDateChangeListener provideDateChangedListener(){
        return new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                alarmDate.set(Calendar.DAY_OF_MONTH,i2);
                alarmDate.set(Calendar.MONTH,i1);
                alarmDate.set(Calendar.YEAR,i);
                addTaskPresenter.userClickedCalendarView(i2,i1,i);
            }
        };
    }

    private TimePicker.OnTimeChangedListener provideTimeChangedListener(){
        return new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                alarmDate.set(Calendar.HOUR_OF_DAY,i);
                alarmDate.set(Calendar.MINUTE,i1);
                addTaskPresenter.userClickedTimePicker(i,i1);
            }
        };
    }

    @Override
    public void updateDateTextView(String date) {
        txtTaskDate.setText(date);
    }

    @Override
    public void updateHourTextView(String hour) {
        txtTaskHour.setText(hour);
    }

    @Override
    public void resetScreen() {
        edtTaskTitle.setText("");
        edtTaskDetail.setText("");
        edtTaskPlace.setText("");
        txtTaskHour.setText("");
        txtTaskDate.setText("");
        txtPlaceIsSelected.setText("");
    }

    @Override
    public void showTaskErrorMessage() {
        Log.e("AddTaskError","Algun camp esta vacio");
    }

    @Override
    public void startMapActivity() {
        startActivityForResult(new Intent(this, MapsActivity.class),MAP_ACTIVITY_REQUEST);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void createAlarm(Calendar alarm,String alarmTitle, String alarmDetail) {
        Intent intent = new Intent(this,AlarmService.class);
        intent.putExtra("alarmTitle",alarmTitle);
        intent.putExtra("alarmDetail",alarmDetail);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,ALARM_REQUEST_CODE,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager =  (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,alarm.getTimeInMillis()-HOUR_MILISECS,AlarmManager.INTERVAL_DAY,pendingIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MAP_ACTIVITY_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                txtPlaceIsSelected.setText("Lugar del evento listo");
                this.latitude = data.getStringExtra("latitude");
                this.longitude = data.getStringExtra("longitude");
            }else {
                txtPlaceIsSelected.setText("Falta lugar del evento");
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view == btnSendTask){
            addTaskPresenter.setAlarm(alarmDate,edtTaskTitle.getText().toString(),edtTaskDetail.getText().toString());
            addTaskPresenter.userClickedSendTaskButton(
                    edtTaskTitle.getText().toString(),
                    edtTaskDetail.getText().toString(),
                    txtTaskDate.getText().toString(),
                    txtTaskHour.getText().toString(),
                    edtTaskPlace.getText().toString(),
                    latitude,
                    longitude);
        }else if(view == btnOpenMap){
            addTaskPresenter.userClickedMapButton();
        }
    }
}
