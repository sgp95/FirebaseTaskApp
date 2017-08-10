package com.sgp95.santiago.firebasetaskapp.presenter;

import com.sgp95.santiago.firebasetaskapp.model.TaskModel;
import com.sgp95.santiago.firebasetaskapp.util.StringUtils;
import com.sgp95.santiago.firebasetaskapp.view.ViewHolderView;

public class ViewHolderPresenterImpl implements ViewHolderPresenter {
    private ViewHolderView viewHolderView;

    public ViewHolderPresenterImpl() {
    }

    @Override
    public void setView(ViewHolderView view) {
        this.viewHolderView = view;
    }

    @Override
    public void onReceiveTask(TaskModel task) {
        if(task != null && !StringUtils.stringsAreNullOrEmpty(task.getTaskTitle(),task.getTaskDate(),task.getTaskHour())){
            viewHolderView.setTitle(task.getTaskTitle());
            viewHolderView.setDate(task.getTaskDate());
            viewHolderView.setHour(task.getTaskHour());
        }

    }
}
