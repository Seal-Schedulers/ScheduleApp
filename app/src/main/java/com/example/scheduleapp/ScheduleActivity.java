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

        Log.d("ScheduleActivity", "getting all tasks for today");
        Controller aController = (Controller) getApplicationContext();
        Log.d("ScheduleActivity", "past the the controller!");

        ArrayList<String> items = new ArrayList<String>();
        items.add("apples");
        items.add("bananas");
        items.add("strawberries");

        ListView scheduleDisplay =(ListView)findViewById(R.id.scheduleDisplayList);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        scheduleDisplay.setAdapter(adapter);
        /*Log.d("ScheduleActivity", "entering the loop");
        if (aController.getDaysHashMap().containsKey(today)){
            Log.d("ScheduleActivity", "in the loop");
            for(String s : aController.getTaskFromDayList(LocalDate.of(year,month,dayOfMonth))) {
                Log.d("ScheduleActivity", "done");
            }
        }*/

    }

    public void goToCalendar(View v) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

}
