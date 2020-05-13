package com.example.scheduleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendar = (CalendarView) findViewById(R.id.calendarView);

        Toast.makeText(this, "None 1", Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, "None", Toast.LENGTH_SHORT).show();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                Intent intent = new Intent(CalendarActivity.this, ScheduleActivity.class);

                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("dayOfMonth", dayOfMonth);

                startActivity(intent);
            }
        });
    }

    /*public void readCSV() {
        try {
            FileInputStream fileInputStream = openFileInput("text");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            StringBuffer sb = new StringBuffer();
            String line = reader.readLine();

            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
            Toast.makeText(this, "read line", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "could not read line", Toast.LENGTH_SHORT).show();
        }
    }

    public void writeCSV(String sFileName, String sBody) {
        File file = new File(getFilesDir(),"appData");
        if(!file.exists()){
            file.mkdir();
        }

        try{
            File gpxfile = new File(file, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(this, "written line", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();

        }
    }*/

}
