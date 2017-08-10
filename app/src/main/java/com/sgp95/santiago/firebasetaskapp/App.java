package com.sgp95.santiago.firebasetaskapp;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.sgp95.santiago.firebasetaskapp.api.authentication.AuthenticationHelper;
import com.sgp95.santiago.firebasetaskapp.api.authentication.AuthenticationHelperImpl;
import com.sgp95.santiago.firebasetaskapp.api.database.DatabaseHelper;
import com.sgp95.santiago.firebasetaskapp.api.database.DatabaseHelperImpl;

public class App extends Application {
    //Paginas
    /*
    * https://github.com/filbabic/FirebaseChatApp
    * https://medium.com/@fbabic/exploring-the-new-google-firebase-f38a50ed7a9a
    * https://medium.cobeisfresh.com/using-firebase-to-create-a-simple-chat-application-in-android-4b32fdbf565e*/





    public static App mInstance;

    private DatabaseHelper databaseHelper;
    private AuthenticationHelper authenticationHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        if(!FirebaseApp.getApps(this).isEmpty()){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            mInstance = this;
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            databaseHelper = new DatabaseHelperImpl(firebaseDatabase);
            authenticationHelper = new AuthenticationHelperImpl(firebaseAuth);
        }
    }

    public static App get(){
        return mInstance;
    }

    public DatabaseHelper getDatabaseHelper(){
        return databaseHelper;
    }

    public AuthenticationHelper getAuthenticationHelper(){
        return authenticationHelper;
    }
}
