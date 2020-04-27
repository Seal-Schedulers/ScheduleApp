package com.example.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }

    private String getTaskNameText() {
        EditText taskNameText = findViewById(R.id.inputTaskName);
        String taskName = taskNameText.getText().toString();
        return taskName;
    }

    private double getHours() {
        EditText hoursText = findViewById(R.id.inputHours);
        String hoursStr = hoursText.getText().toString();
        if (hoursStr.isEmpty())
            hoursStr = "0";
        double hoursNum = Double.parseDouble(hoursStr);
        return hoursNum;
    }

    private double getDaysTillDue() {
        EditText daysTillDueText = findViewById(R.id.inputDaysTillDue);
        String daysTillDueStr = daysTillDueText.getText().toString();
        if (daysTillDueStr.isEmpty())
            daysTillDueStr = "0";
        double daysTillDueNum = Double.parseDouble(daysTillDueStr);
        return daysTillDueNum;
    }

    public void createTask(View v) {
        TextView taskNameLabel = findViewById(R.id.inputTaskName);
        String taskNameText = getTaskNameText();
        taskNameLabel.setText(getTaskNameText());

        TextView hoursLabel = findViewById(R.id.inputHours);
        String hoursStr = Double.toString(getHours());
        hoursLabel.setText(hoursStr);

        TextView daysTillDueLabel = findViewById(R.id.inputDaysTillDue);
        String daysTillDueStr = Double.toString(getDaysTillDue());
        hoursLabel.setText(daysTillDueStr);

        Intent intent = new Intent(this, TasksActivity.class);
        intent.putExtra("task", taskNameText);
        intent.putExtra("hours", hoursStr);
        intent.putExtra("days", daysTillDueStr);
        startActivity(intent);
    }
}
