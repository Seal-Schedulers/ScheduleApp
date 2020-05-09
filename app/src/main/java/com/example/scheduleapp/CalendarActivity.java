package com.example.scheduleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.File;
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
        writeCSV();
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

    public void writeCSV() {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File("C:\\Users\\15088\\Documents\\11th\\CS\\AndroidStuffStuff\\ScheduleApp\\app\\src\\main\\res\\raw\\test.csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputFile);

            // adding header to csv
            String[] header = { "Name", "Class", "Grades", "Favorite Subject"};
            writer.writeNext(header);

            // add data to csv
            String[] data1 = { "Noe", "11", "100", "Math" };
            writer.writeNext(data1);
            String[] data2 = { "Advika", "11", "100", "Chemistry" };
            writer.writeNext(data2);


            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void readCSV() {
        try {
            InputStream is = getResources().openRawResource(R.raw.test);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            CSVReader reader1 = new CSVReader(reader);
            String[] nextLine;
            while ((nextLine = reader1.readNext()) != null) {
                // nextLine[] is an array of values from the line
                System.out.println(nextLine[0] + nextLine[1] + "etc...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "The specified file was not found", Toast.LENGTH_SHORT).show();
        }

    }

}
