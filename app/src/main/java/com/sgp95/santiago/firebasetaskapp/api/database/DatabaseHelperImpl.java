package com.sgp95.santiago.firebasetaskapp.api.database;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.sgp95.santiago.firebasetaskapp.constants.Constants;
import com.sgp95.santiago.firebasetaskapp.constants.FirebaseConstants;
import com.sgp95.santiago.firebasetaskapp.model.TaskModel;
import com.sgp95.santiago.firebasetaskapp.model.UserModel;

public class DatabaseHelperImpl implements DatabaseHelper{
    private final FirebaseDatabase firebaseDatabase;
    private Query taskQuery;

    public DatabaseHelperImpl(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }


    @Override
    public void receiveTaskFromFirebase(ChildEventListener listener) {
        firebaseDatabase
                .getReference(FirebaseConstants.TASK_REFERENCE)
                .limitToLast(Constants.TASKS_LIMIT)
                .addChildEventListener(listener);
    }

    @Override
    public void sendTaskToFirebase(String title, String detail, String date,String hour, String place, String latitude, String longitude) {
        TaskModel taskModel = new TaskModel(title,detail,date,hour,place,latitude,longitude);
        firebaseDatabase
                .getReference(FirebaseConstants.TASK_REFERENCE)
                .push()
                .setValue(taskModel);
    }

    @Override
    public void removeTaskCallback(ChildEventListener listener) {
        firebaseDatabase
                .getReference(FirebaseConstants.TASK_REFERENCE)
                .removeEventListener(listener);
    }

    @Override
    public void setUserIsOnline(boolean isOnline, String userName) {
        UserModel userModel = new UserModel(userName,isOnline);
        firebaseDatabase
                .getReference(FirebaseConstants.USERS_REFERENCE)
                .child(userName)
                .setValue(userModel);
    }
}
