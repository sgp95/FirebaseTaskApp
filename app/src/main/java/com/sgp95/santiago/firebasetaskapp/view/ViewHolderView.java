package com.sgp95.santiago.firebasetaskapp.view;

import com.sgp95.santiago.firebasetaskapp.model.TaskModel;

public interface ViewHolderView {
    void setTitle(String taskTitle);

    void setDate(String date);

    void setHour(String hour);

    void receiveTask(TaskModel task);
}
