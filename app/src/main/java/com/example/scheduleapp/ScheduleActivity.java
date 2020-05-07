package com.example.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.time.LocalDate;

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
    }

    public void goToCalendar(View v) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

}
