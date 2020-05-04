package com.example.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Intent intent = getIntent();

        String taskNameText = intent.getStringExtra("task");
        TextView taskNameLabel = findViewById(R.id.taskNameLabel);
        taskNameLabel.setText(taskNameText);

        String hoursStr = intent.getStringExtra("hours");
        TextView hoursLabel = findViewById(R.id.hoursLabel);
        hoursLabel.setText(hoursStr);

        String daysTillDueStr = intent.getStringExtra("days");
        TextView dueDateLabel = findViewById(R.id.daysTillDueLabel);
        dueDateLabel.setText(daysTillDueStr);

        String LookUp = intent.getStringExtra("LookUp");
        TextView taskNumberLabel = findViewById(R.id.taskNumberLabel);
        taskNumberLabel.setText(LookUp);
    }

    public void goToCreateTask(View v) {
        Intent intent = new Intent(this, CreateActivity.class);
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
