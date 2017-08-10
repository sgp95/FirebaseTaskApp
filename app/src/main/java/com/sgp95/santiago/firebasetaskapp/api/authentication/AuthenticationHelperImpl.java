package com.sgp95.santiago.firebasetaskapp.api.authentication;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sgp95.santiago.firebasetaskapp.api.RequestListener;


public class AuthenticationHelperImpl implements AuthenticationHelper {

    private final FirebaseAuth firebaseAuth;

    public AuthenticationHelperImpl(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public void logTheUserIn(String email, String password, final RequestListener listener) {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if(user != null){
                        listener.onSuccessfulRequest();
                    }
                }else {
                    listener.onFailedRequest();
                }

            }
        });
    }

    @Override
    public void logTheUserOut() {
        firebaseAuth.signOut();

    }

    @Override
    public boolean checkIfUserLoggedIn() {
        return firebaseAuth.getCurrentUser() != null;
    }

    @Override
    public String getCurrentUserDBName() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        String[] currentUserDBName = currentUser.getEmail().split("@");
        return currentUserDBName[0];
    }
}
