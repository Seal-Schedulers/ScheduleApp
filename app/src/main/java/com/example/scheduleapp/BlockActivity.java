package com.example.scheduleapp;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class BlockActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Time startTime;
    private Time endTime;
    private ArrayList<Time> dayTimes = new ArrayList<>();{
        Time time = new Time();
        do {
            dayTimes.add(new Time(time));
            time.increment();
        }
        while(!time.equals(new Time(0,0)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);

        ArrayList<String> times =  new ArrayList<String>();
        Time time = new Time(0,0);
        do {
            times.add(new Time(time).toString());
            time.increment();
        }
        while(!time.equals(new Time(0,0)));

        Spinner startTimes = (Spinner) findViewById(R.id.startTime);
        Spinner endTimes = (Spinner) findViewById(R.id.endTime);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, times);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startTimes.setAdapter(adapter);
        startTimes.setOnItemSelectedListener(this);
        endTimes.setAdapter(adapter);
        endTimes.setOnItemSelectedListener(this);
    }

    public void blockTime(View v) {
        Controller aController = (Controller) getApplicationContext();
        EditText blockNameText = findViewById(R.id.blockedActivityName);
        String blockName = blockNameText.getText().toString();

        EditText blockYearText = findViewById(R.id.inputDateYear);
        int blockYear = Integer.parseInt(blockYearText.getText().toString());

        EditText blockMonthText = findViewById(R.id.inputDateMonth);
        int blockMonth = Integer.parseInt(blockMonthText.getText().toString());

        EditText blockDayText = findViewById(R.id.inputDateDay);
        int blockDay = Integer.parseInt(blockDayText.getText().toString());

        aController.createBlockTask(blockName, startTime, endTime, true, LocalDate.of(blockYear, blockMonth, blockDay));
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner time1 = (Spinner)parent;
        Spinner time2 = (Spinner)parent;
        if(time1.getId() == R.id.startTime) {
            this.startTime = dayTimes.get(position);
        }
        if(time2.getId() == R.id.endTime) {
            this.endTime = dayTimes.get(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
