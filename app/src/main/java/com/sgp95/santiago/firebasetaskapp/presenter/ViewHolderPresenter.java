package com.sgp95.santiago.firebasetaskapp.presenter;

import com.sgp95.santiago.firebasetaskapp.model.TaskModel;
import com.sgp95.santiago.firebasetaskapp.view.ViewHolderView;

public interface ViewHolderPresenter extends Presenter<ViewHolderView> {

    void onReceiveTask(TaskModel task);
}
