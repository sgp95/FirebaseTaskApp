package com.sgp95.santiago.firebasetaskapp.presenter;

import com.sgp95.santiago.firebasetaskapp.view.LoginView;

public interface LoginPresenter extends Presenter<LoginView> {

    void userClickedLoginButton(String email,String password);

    void checkifUserIsLoggedIn();
}
