package com.example.scheduleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.time.LocalDate;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendar = (CalendarView) findViewById(R.id.calendarView);

        //Toast.makeText(this, "None", Toast.LENGTH_SHORT).show();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Controller aController = (Controller) getApplicationContext();
                if(aController.getDaysHashMap().containsKey(LocalDate.of(year, month+1, dayOfMonth))){
                    Intent intent = new Intent(CalendarActivity.this, ScheduleActivity.class);

                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                    intent.putExtra("dayOfMonth", dayOfMonth);

                    startActivity(intent);
                }
                else {
                    Toast.makeText(aController, "Nothing scheduled for that day!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void goToTasks(View v) {
        Intent intent = new Intent(CalendarActivity.this, CreateActivity.class);
        startActivity(intent);
    }
}
