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

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity implements TaskDisplayAdapter.OnNoteListener{

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

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: clicked");

        Intent intent = new Intent(this, RemoveActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public void goToCreateTask(View v) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    public void goToBlockTime(View v) {
        Intent intent = new Intent(this, BlockActivity.class);
        startActivity(intent);
    }

    public void goToCalendar(View v) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void goToSchedule(View v) throws Exception {
        Controller aController = (Controller) getApplicationContext();

        /*for(TaskDisplay task: list){
            aController.createTask(task.getTaskName(), Double.valueOf(task.getHours()), Integer.valueOf(task.getDays()));
        }*/

        //aController.createTask("math", 3.0, 2);

        Toast.makeText(getApplicationContext(), "Scheduled tasks",Toast.LENGTH_LONG).show();

        list.clear();
        adapter.notifyDataSetChanged();

        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }

    public void goToMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}
