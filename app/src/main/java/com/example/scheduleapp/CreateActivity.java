package com.example.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CreateActivity extends AppCompatActivity {

    //Here you can put the data. In this case all we need as an ArrayList of String arrays that will hold the tasks.
    //You can call it TaskHolder or something

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }
    //You don't necessarily need these following three methods because although they retrieve the data from the user,
    //it converts the strings into doubles which isn't needed. Don't delete them yet, just comment them out - we never know, we
    //might need them later.
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

    private int getDaysTillDue() {
        EditText daysTillDueText = findViewById(R.id.inputDaysTillDue);
        String daysTillDueStr = daysTillDueText.getText().toString();
        if (daysTillDueStr.isEmpty())
            daysTillDueStr = "0";
        int daysTillDueNum = Integer.parseInt(daysTillDueStr);
        return daysTillDueNum;
    }

    //As I mentioned above, you shouldn't use the methods above in this method because it makes them from String to double and
    //all you are doing here is converting them back to String. Instead retrieve them directly as Strings in this method. It
    //will save the extra step. Create a String array with the name, hours, and daysTillDue and put it in the ArrayList created above.
    public void createTask(View v) {
        TextView taskNameLabel = findViewById(R.id.inputTaskName);
        String taskNameText = getTaskNameText();
        taskNameLabel.setText(getTaskNameText());

        TextView hoursLabel = findViewById(R.id.inputHours);
        String hoursStr = Double.toString(getHours());
        hoursLabel.setText(hoursStr);

        TextView daysTillDueLabel = findViewById(R.id.inputDaysTillDue);
        String daysTillDueStr = Integer.toString(getDaysTillDue());
        hoursLabel.setText(daysTillDueStr);

        Intent intent = new Intent(this, TasksActivity.class);
        intent.putExtra("task", taskNameText);
        intent.putExtra("hours", hoursStr);
        intent.putExtra("days", daysTillDueStr);
        startActivity(intent);
    }

    //I know I commented a lot... sorry about that hehe. Let me know if I made a mistake somewhere or if you have any questions
    //about what I said.

    public void scheduleTasks(){

    }
}
