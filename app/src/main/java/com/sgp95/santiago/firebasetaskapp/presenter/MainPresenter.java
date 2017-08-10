package com.sgp95.santiago.firebasetaskapp.presenter;

import com.sgp95.santiago.firebasetaskapp.model.TaskModel;
import com.sgp95.santiago.firebasetaskapp.view.MainView;

public interface MainPresenter extends Presenter<MainView> {
    void removeTaskCallbacks();

    void RequestTasksFromNetwork();

    void logTheUserIn();

    void logTheUserOut();

    void checkIfUserIsLoggedIn();

    void userClickedNewTaskButton();

    void userClickedOptionLogout();

    void userClickedItem(TaskModel taskModel);
}
