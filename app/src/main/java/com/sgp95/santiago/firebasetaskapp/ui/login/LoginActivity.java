package com.sgp95.santiago.firebasetaskapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.sgp95.santiago.firebasetaskapp.App;
import com.sgp95.santiago.firebasetaskapp.R;
import com.sgp95.santiago.firebasetaskapp.presenter.LoginPresenter;
import com.sgp95.santiago.firebasetaskapp.presenter.LoginPresenterImpl;
import com.sgp95.santiago.firebasetaskapp.ui.main.MainActivity;
import com.sgp95.santiago.firebasetaskapp.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    @BindView(R.id.edtUserEmail)
    EditText edtUserEmail;

    @BindView(R.id.edtUserPassword)
    EditText edtUserPassword;

    @BindView(R.id.btnLogin)
    Button loginButton;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initPresenter();
        checkifUserIsLoggedIn();
        intUi();

    }

    private void checkifUserIsLoggedIn(){
        loginPresenter.checkifUserIsLoggedIn();
    }

    private void initPresenter(){
        loginPresenter = new LoginPresenterImpl(App.get().getAuthenticationHelper());
        loginPresenter.setView(this);
    }

    private void intUi(){
        loginButton.setOnClickListener(this);
    }

    @Override
    public void showOnFailedTologinErrorMessage() {
        Snackbar.make(scrollView,"Login Fallido",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showOnSuccessfulLoginMessage() {
        Snackbar.make(scrollView,"Login Exitoso",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void showOnEmptyEmailOrPasswordError() {
        Snackbar.make(scrollView,"Email y/o Contraseña estan vacios",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideEditTextKeyboard() {
        View currentView = getCurrentFocus();
        if(currentView != null){
            InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(currentView.getWindowToken(),0);
        }
    }

    @Override
    public void onClick(View view) {
        if(view == loginButton){
            loginPresenter.userClickedLoginButton(edtUserEmail.getText().toString(),edtUserPassword.getText().toString());
        }
    }
}
