package com.example.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }

    /**
     * adds tasks to the recyclerview in the taskActivity
     * @param v
     */
    public void createTemporaryTask(View v) {
        EditText taskNameText = findViewById(R.id.inputTaskName);
        String taskName = taskNameText.getText().toString();

        EditText hoursText = findViewById(R.id.inputHours);
        String hoursStr = hoursText.getText().toString();

        EditText daysTillDueText = findViewById(R.id.inputDaysTillDue);
        String daysTillDueStr = daysTillDueText.getText().toString();

        TasksActivity.list.add(new TaskDisplay(taskName, hoursStr, daysTillDueStr));

        Intent intent = new Intent(this, TasksActivity.class);
        startActivity(intent);

    }

}
