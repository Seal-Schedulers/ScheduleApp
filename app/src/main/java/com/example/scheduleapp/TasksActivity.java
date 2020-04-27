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

        String dueDateStr = intent.getStringExtra("dueDate");
        TextView dueDateLabel = findViewById(R.id.dueDateLabel);
        dueDateLabel.setText(dueDateStr);
    }

    public void goToCreateTask(View v) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }


}
