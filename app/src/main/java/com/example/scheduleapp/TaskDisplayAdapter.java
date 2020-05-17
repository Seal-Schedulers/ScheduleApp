package com.example.scheduleapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskDisplayAdapter extends RecyclerView.Adapter<TaskDisplayAdapter.ViewHolder> {

    //data
    private ArrayList<TaskDisplay> tasksToDisplay;
    private OnNoteListener mOnNoteListener;

    /**
     * constructs a taskDisplayAdapter object
     * @param tasksToDisplay: list of tasks to display
     * @param onNoteListener: onclick listener
     */
    public TaskDisplayAdapter(ArrayList<TaskDisplay> tasksToDisplay, OnNoteListener onNoteListener) {
        this.tasksToDisplay = tasksToDisplay;
        this.mOnNoteListener = onNoteListener;
    }

    /**
     * method for inflating the RecyclerView
     * @param parent
     * @param viewType
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(v, mOnNoteListener);
    }

    /**
     * binds the data from TaskDisplay class to the xml layout in the RecyclerView
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskDisplay task = tasksToDisplay.get(position);

        holder.taskNameLabel.setText(task.getTaskName().toUpperCase());
        holder.hoursStr.setText("Hours: " + task.getHours());
        holder.daysStr.setText("Days Until Due: " + task.getDays());
    }

    /**
     * @return size of the taskToDisplay list
     */
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

        /**
         * constructs a ViewHolder object
         * @param view
         * @param onNoteListener
         */
        public ViewHolder(View view, OnNoteListener onNoteListener) {
            super(view);
            this.view = view;
            taskNameLabel = view.findViewById(R.id.taskNameLabel);
            hoursStr = view.findViewById(R.id.hoursLabel);
            daysStr = view.findViewById(R.id.daysTillDueLabel);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        /**
         * onClick method
         */
        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}