package com.sgp95.santiago.firebasetaskapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sgp95.santiago.firebasetaskapp.App;
import com.sgp95.santiago.firebasetaskapp.R;
import com.sgp95.santiago.firebasetaskapp.model.TaskModel;
import com.sgp95.santiago.firebasetaskapp.presenter.MainPresenter;
import com.sgp95.santiago.firebasetaskapp.presenter.MainPresenterImpl;
import com.sgp95.santiago.firebasetaskapp.ui.add.AddTaskActivity;
import com.sgp95.santiago.firebasetaskapp.ui.detail.TaskDetailActivity;
import com.sgp95.santiago.firebasetaskapp.ui.login.LoginActivity;
import com.sgp95.santiago.firebasetaskapp.ui.main.adapter.TaskAdapter;
import com.sgp95.santiago.firebasetaskapp.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener,TaskAdapter.ClickListener{
    private MainPresenter mainPresenter;
    private TaskAdapter taskAdapter;

    @BindView(R.id.activity_main_recyclerview)
    RecyclerView taskRecyclerView;

    @BindView(R.id.activity_main_fab)
    FloatingActionButton fabNewTask;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPresenter();
        initAdapter();
        initUi();
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                mainPresenter.userClickedOptionLogout();
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void initPresenter(){
        mainPresenter = new MainPresenterImpl(App.get().getDatabaseHelper(),App.get().getAuthenticationHelper());
        mainPresenter.setView(this);
    }

    private void initAdapter(){
        taskAdapter = new TaskAdapter();
        taskAdapter.setClickListener(this);
    }

    private void initUi(){
        fabNewTask.setOnClickListener(this);
        taskRecyclerView.setHasFixedSize(true);
        taskRecyclerView.setItemAnimator(new DefaultItemAnimator());
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(App.get()));
        taskRecyclerView.setAdapter(taskAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.logTheUserIn();
        taskAdapter.clearTaskList();
        mainPresenter.RequestTasksFromNetwork();
    }

    @Override
    public void onBackPressed() {
        mainPresenter.logTheUserOut();
        taskAdapter.clearTaskList();
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    @Override
    protected void onPause() {
        if(mainPresenter != null){
            mainPresenter.checkIfUserIsLoggedIn();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        taskAdapter.clearTaskList();
        super.onDestroy();
    }


    @Override
    public void startCreateTaskActivity() {
        startActivity(new Intent(this, AddTaskActivity.class));
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void sendNewTaskToAdapter(TaskModel taskModel) {
        taskAdapter.addNewTask(taskModel);
        taskRecyclerView.scrollToPosition(taskAdapter.getItemCount()-1);
    }

    @Override
    public void removeCallbackFromFirebase() {
        mainPresenter.removeTaskCallbacks();
    }

    @Override
    public void returnLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }

    @Override
    public void startTaskDetailActivity(TaskModel taskModel) {
        Intent detailIentent = new Intent(this, TaskDetailActivity.class);
        detailIentent.putExtra("Title",taskModel.getTaskTitle());
        detailIentent.putExtra("Detail",taskModel.getTaskDetail());
        detailIentent.putExtra("Date",taskModel.getTaskDate());
        detailIentent.putExtra("Hour",taskModel.getTaskHour());
        detailIentent.putExtra("Place",taskModel.getTaskPlace());
        detailIentent.putExtra("Latitude",taskModel.getLatitude());
        detailIentent.putExtra("Longitude",taskModel.getLongitude());
        startActivity(detailIentent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void onClick(View view) {
        if(view == fabNewTask){
            mainPresenter.userClickedNewTaskButton();
        }
    }

    @Override
    public void ItemClicked(TaskModel taskModel) {
        mainPresenter.userClickedItem(taskModel);
    }
}
