package com.sgp95.santiago.firebasetaskapp.presenter;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.sgp95.santiago.firebasetaskapp.api.authentication.AuthenticationHelper;
import com.sgp95.santiago.firebasetaskapp.api.database.DatabaseHelper;
import com.sgp95.santiago.firebasetaskapp.model.TaskModel;
import com.sgp95.santiago.firebasetaskapp.util.StringUtils;
import com.sgp95.santiago.firebasetaskapp.view.MainView;

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;

    private final DatabaseHelper databaseHelper;
    private final AuthenticationHelper authenticationHelper;

    public MainPresenterImpl(DatabaseHelper databaseHelper, AuthenticationHelper authenticationHelper) {
        this.databaseHelper = databaseHelper;
        this.authenticationHelper = authenticationHelper;
    }

    @Override
    public void removeTaskCallbacks() {
        databaseHelper.removeTaskCallback(provideTaskCallback());
    }

    @Override
    public void RequestTasksFromNetwork() {
        databaseHelper.receiveTaskFromFirebase(provideTaskCallback());
    }

    @Override
    public void logTheUserIn() {
        String userName = authenticationHelper.getCurrentUserDBName();
        if(!StringUtils.stringsAreNullOrEmpty(userName)){
            databaseHelper.setUserIsOnline(true,userName);
        }
    }

    @Override
    public void logTheUserOut() {
        String userName = authenticationHelper.getCurrentUserDBName();
        if(!StringUtils.stringsAreNullOrEmpty(userName)){
            databaseHelper.setUserIsOnline(false,userName);
        }
        authenticationHelper.logTheUserOut();
    }

    @Override
    public void checkIfUserIsLoggedIn() {
        if(authenticationHelper.checkIfUserLoggedIn()){
            mainView.removeCallbackFromFirebase();
        }
    }

    @Override
    public void userClickedNewTaskButton() {
        mainView.startCreateTaskActivity();
    }

    @Override
    public void userClickedOptionLogout() {
        logTheUserOut();
        mainView.returnLoginActivity();
    }

    @Override
    public void userClickedItem(TaskModel taskModel) {
        mainView.startTaskDetailActivity(taskModel);
    }

    protected ChildEventListener provideTaskCallback(){
        return new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TaskModel taskModel = dataSnapshot.getValue(TaskModel.class);
                if(taskModel != null){
                    mainView.sendNewTaskToAdapter(taskModel);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if(databaseError !=  null){
                    Log.e("MainPresenter",databaseError.toString());
                }
            }
        };
    }

    @Override
    public void setView(MainView view) {
        this.mainView = view;
    }
}
