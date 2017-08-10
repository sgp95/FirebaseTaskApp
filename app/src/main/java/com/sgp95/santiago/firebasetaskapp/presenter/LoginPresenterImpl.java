package com.sgp95.santiago.firebasetaskapp.presenter;

import com.sgp95.santiago.firebasetaskapp.api.RequestListener;
import com.sgp95.santiago.firebasetaskapp.api.authentication.AuthenticationHelper;
import com.sgp95.santiago.firebasetaskapp.util.StringUtils;
import com.sgp95.santiago.firebasetaskapp.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView loginView;
    private final AuthenticationHelper authenticationHelper;

    public LoginPresenterImpl(AuthenticationHelper authenticationHelper) {
        this.authenticationHelper = authenticationHelper;
    }


    @Override
    public void setView(LoginView view) {
        this.loginView = view;
    }

    @Override
    public void userClickedLoginButton(String email, String password) {
        loginView.hideEditTextKeyboard();
        loginView.showProgressBar();
        if(StringUtils.stringsAreNullOrEmpty(email,password)){
            loginView.showOnEmptyEmailOrPasswordError();
            loginView.hideProgressBar();
        }else {
            authenticationHelper.logTheUserOut();
            authenticationHelper.logTheUserIn(email,password,provideLoginCallback());
        }
    }

    @Override
    public void checkifUserIsLoggedIn() {
        if(authenticationHelper.checkIfUserLoggedIn()){
            loginView.startMainActivity();
        }
    }

    protected RequestListener provideLoginCallback(){
        return new RequestListener() {
            @Override
            public void onSuccessfulRequest() {
                loginView.hideProgressBar();
                loginView.showOnSuccessfulLoginMessage();
                loginView.startMainActivity();
            }

            @Override
            public void onFailedRequest() {
                loginView.hideProgressBar();
                loginView.showOnFailedTologinErrorMessage();
            }
        };
    }
}
