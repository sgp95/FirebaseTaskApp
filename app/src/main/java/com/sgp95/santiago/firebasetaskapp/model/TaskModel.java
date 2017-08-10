package com.sgp95.santiago.firebasetaskapp.model;

import com.sgp95.santiago.firebasetaskapp.util.StringUtils;

public class TaskModel extends BaseModel{
    private String taskTitle;
    private String taskDetail;
    private String taskDate;
    private String taskHour;
    private String taskPlace;
    private String latitude;
    private String longitude;

    public TaskModel() {}

    public TaskModel(String taskTitle,
                     String taskDetail,
                     String taskDate,
                     String taskHour,
                     String taskPlace,
                     String latitude,
                     String longitude) {
        this.taskTitle = taskTitle;
        this.taskDetail = taskDetail;
        this.taskDate = taskDate;
        this.taskHour = taskHour;
        this.taskPlace = taskPlace;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTaskHour() {
        return taskHour;
    }

    public void setTaskHour(String taskHour) {
        this.taskHour = taskHour;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDetail() {
        return taskDetail;
    }

    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskPlace() {
        return taskPlace;
    }

    public void setTaskPlace(String taskPlace) {
        this.taskPlace = taskPlace;
    }

    @Override
    protected boolean validate() {
        return !StringUtils.stringsAreNullOrEmpty(taskTitle,taskDetail,taskDate,taskPlace);
    }
}
