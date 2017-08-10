package com.sgp95.santiago.firebasetaskapp.view;

import com.sgp95.santiago.firebasetaskapp.model.TaskModel;

public interface MainView {
    void startCreateTaskActivity();

    void sendNewTaskToAdapter(TaskModel taskModel);

    void removeCallbackFromFirebase();

    void returnLoginActivity();

    void startTaskDetailActivity(TaskModel taskModel);
}
