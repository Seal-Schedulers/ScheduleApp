package com.example.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {

    private RecyclerView tasksToDisplay;
    private RecyclerView.Adapter adapter;
    static ArrayList<TaskDisplay> list = new ArrayList<TaskDisplay>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Toast.makeText(getApplicationContext(),"Hello Javatpoint 1",Toast.LENGTH_SHORT).show();

        this.tasksToDisplay = (RecyclerView) findViewById(R.id.taskDisplay);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.tasksToDisplay.setLayoutManager(mLayoutManager);

        adapter = new TaskDisplayAdapter(list);
        this.tasksToDisplay.setAdapter(adapter);
    }

    public void goToCreateTask(View v) {
        Intent intent = new Intent(this, CreateActivity.class);
        intent.putParcelableArrayListExtra("tasksToDisplay", list);
        startActivity(intent);
    }

    public void goToBlockTime(View v) {
        Intent intent = new Intent(this, BlockActivity.class);
        startActivity(intent);
    }

    public void goToRemoveTask(View v) {
        Intent intent = new Intent(this, RemoveActivity.class);
        startActivity(intent);
    }

    public void goToSchedule(View v) {
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }



}
