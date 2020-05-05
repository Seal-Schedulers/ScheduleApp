package com.example.scheduleapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scheduleapp.TaskDisplay;

import java.util.ArrayList;

public class TaskDisplayAdapter extends RecyclerView.Adapter<TaskDisplayAdapter.ViewHolder> {

    private ArrayList<TaskDisplay> tasksToDisplay;

    public TaskDisplayAdapter(ArrayList<TaskDisplay> tasksToDisplay) {
        this.tasksToDisplay = tasksToDisplay;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskDisplay task = tasksToDisplay.get(position);

        holder.taskNameLabel.setText(task.getTaskName());
        holder.hoursStr.setText(task.getHours());
        holder.daysStr.setText(task.getDays());
    }

    @Override
    public int getItemCount() {
        return tasksToDisplay.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView taskNameLabel;
        public final TextView hoursStr;
        public final TextView daysStr;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            taskNameLabel = view.findViewById(R.id.taskNameLabel);
            hoursStr = view.findViewById(R.id.hoursLabel);
            daysStr = view.findViewById(R.id.daysTillDueLabel);
        }
    }
}