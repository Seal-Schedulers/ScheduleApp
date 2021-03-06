package com.example.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity implements TaskDisplayAdapter.OnNoteListener{

    //Data
    private static final String TAG = "TasksActivity";
    private RecyclerView tasksToDisplay;
    static RecyclerView.Adapter adapter;
    static ArrayList<TaskDisplay> list = new ArrayList<TaskDisplay>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        this.tasksToDisplay = (RecyclerView) findViewById(R.id.taskDisplay);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.tasksToDisplay.setLayoutManager(mLayoutManager);

        adapter = new TaskDisplayAdapter(list, this);
        this.tasksToDisplay.setAdapter(adapter);
    }

    /**
     * redirects to RemoveActivity with the position of
     * the object in the RecyclerView
     */
    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: clicked");

        Intent intent = new Intent(this, RemoveActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    /**
     * redirects to CreateActivity
     */
    public void goToCreateTask(View v) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    /**
     * redirects to BlockTime
     */
    public void goToBlockTime(View v) {
        Intent intent = new Intent(this, BlockActivity.class);
        startActivity(intent);
    }

    /**
     * redirects to CalendarActivity
     */
    public void goToCalendar(View v) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    /**
     * schedules all the tasks in the list of TaskDisplay objects
     * redirects to the schedule for today
     */
    public void goToSchedule(View v) throws Exception {
        Controller aController = (Controller) getApplicationContext();
        Log.d("TasksActivity", "getting ready to create task");
        for(TaskDisplay task: list){
            aController.createTask(task.getTaskName(), Double.valueOf(task.getHours()), Integer.valueOf(task.getDays()), true, LocalDate.now());
        }

        //aController.createTask("math", 20, 10, true);
        Log.d("TasksActivity", "task created");
        Toast.makeText(getApplicationContext(), "Scheduled tasks",Toast.LENGTH_LONG).show();

        list.clear();
        adapter.notifyDataSetChanged();

        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }
}
