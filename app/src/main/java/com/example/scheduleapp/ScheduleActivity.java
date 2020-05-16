package com.example.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    private android.widget.Toast Toast;

    /**
     * displays the tasks for a user selected date
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        LocalDate today = LocalDate.now();

        Intent intent = getIntent();
        int month = intent.getIntExtra("month", (today.getMonthValue()-1));
        int dayOfMonth = intent.getIntExtra("dayOfMonth", today.getDayOfMonth());
        int year = intent.getIntExtra("year", today.getYear());

        String date = (month+1) + "/" + dayOfMonth + "/" + year;

        Toast.makeText(getApplicationContext(), date,Toast.LENGTH_LONG).show();
        Log.d("ScheduleActivity", "date: "+ (month+1) + "/" + dayOfMonth + "/" + year);

        Log.d("ScheduleActivity", "getting all tasks for today");
        Controller aController = (Controller) getApplicationContext();
        Log.d("ScheduleActivity", "past the the controller!");

        /*ArrayList<String> items = new ArrayList<String>();
        items.add("apples");
        items.add("bananas");
        items.add("strawberries");*/

        ListView scheduleDisplay =(ListView)findViewById(R.id.scheduleDisplayList);
        Log.d("ScheduleActivity", "entering the loop");
        Log.d("ScheduleActivity", today.toString());
        Log.d("ScheduleActivity", aController.getDaysHashMap().toString());
        if (aController.getDaysHashMap().containsKey(today)){
            Log.d("ScheduleActivity", "in the loop");
            String[] items = aController.getTaskFromDayList(LocalDate.of(year,month+1, dayOfMonth));
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
            scheduleDisplay.setAdapter(adapter);
        }
        else {
            Log.d("ScheduleActivity", "false");
        }
    }

    /**
     * goes to the CalendarActivity
     * @param v
     */
    public void goToCalendar(View v) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

}
