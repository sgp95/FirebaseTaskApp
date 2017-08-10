package com.sgp95.santiago.firebasetaskapp.view;

public interface LoginView {
    void showOnFailedTologinErrorMessage();

    void showOnSuccessfulLoginMessage();

    void startMainActivity();

    void showOnEmptyEmailOrPasswordError();

    void showProgressBar();

    void hideProgressBar();

    void hideEditTextKeyboard();
}
