package com.sgp95.santiago.firebasetaskapp.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sgp95.santiago.firebasetaskapp.R;
import com.sgp95.santiago.firebasetaskapp.model.TaskModel;
import com.sgp95.santiago.firebasetaskapp.presenter.ViewHolderPresenter;
import com.sgp95.santiago.firebasetaskapp.presenter.ViewHolderPresenterImpl;
import com.sgp95.santiago.firebasetaskapp.view.ViewHolderView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<TaskModel> taskList = new ArrayList<>();
    private ClickListener clickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskModel task = taskList.get(position);
        holder.viewHolderPresenter.onReceiveTask(task);

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void clearTaskList(){
        taskList.clear();
        notifyDataSetChanged();
    }

    public void addNewTask(TaskModel taskModel){
        taskList.add(taskModel);
        notifyDataSetChanged();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ViewHolderView {
        final TextView txtTaskTitle;
        final TextView txtTaskDate;
        final TextView txtTaskHour;

        protected final ViewHolderPresenter viewHolderPresenter;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTaskTitle = itemView.findViewById(R.id.txtTaskTittle);
            txtTaskDate = itemView.findViewById(R.id.txtTaskDate);
            txtTaskHour = itemView.findViewById(R.id.txtTaskHour);

            viewHolderPresenter = new ViewHolderPresenterImpl();
            viewHolderPresenter.setView(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.ItemClicked(taskList.get(getAdapterPosition()));
                    }
                }
            });
        }

        @Override
        public void setTitle(String taskTitle) {
            txtTaskTitle.setText(taskTitle);
        }

        @Override
        public void setDate(String date) {
            txtTaskDate.setText(date);
        }

        @Override
        public void setHour(String hour) {
            txtTaskHour.setText(hour);
        }

        @Override
        public void receiveTask(TaskModel task) {
            viewHolderPresenter.onReceiveTask(task);
        }
    }

    public interface ClickListener {
        void ItemClicked(TaskModel taskModel);
    }
}
