package com.sgp95.santiago.firebasetaskapp.api.database;

import com.google.firebase.database.ChildEventListener;

public interface DatabaseHelper {

    void receiveTaskFromFirebase(ChildEventListener listener);

    void sendTaskToFirebase(String title,
                            String detail,
                            String date,
                            String hour,
                            String place,
                            String latitude,
                            String longitude);

    void removeTaskCallback(ChildEventListener listener);

    void setUserIsOnline(boolean isOnline, String userName);
}
