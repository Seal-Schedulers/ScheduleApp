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
    private OnNoteListener mOnNoteListener;


    public TaskDisplayAdapter(ArrayList<TaskDisplay> tasksToDisplay, OnNoteListener onNoteListener) {
        this.tasksToDisplay = tasksToDisplay;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(v, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskDisplay task = tasksToDisplay.get(position);

        holder.taskNameLabel.setText("Task: " + task.getTaskName().toUpperCase());
        holder.hoursStr.setText("Hours: " + task.getHours());
        holder.daysStr.setText("Days Till Due: " + task.getDays());
    }

    @Override
    public int getItemCount() {
        return tasksToDisplay.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View view;
        public final TextView taskNameLabel;
        public final TextView hoursStr;
        public final TextView daysStr;
        OnNoteListener onNoteListener;

        public ViewHolder(View view, OnNoteListener onNoteListener) {
            super(view);
            this.view = view;
            taskNameLabel = view.findViewById(R.id.taskNameLabel);
            hoursStr = view.findViewById(R.id.hoursLabel);
            daysStr = view.findViewById(R.id.daysTillDueLabel);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}