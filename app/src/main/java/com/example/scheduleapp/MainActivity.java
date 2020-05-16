package com.example.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //readBlockedTaskFile();
        readTaskFile();
    }

    public void goToMyTasks(View v) {
        Intent intent = new Intent(this, TasksActivity.class);
        startActivity(intent);
    }

    public void goToSchedule(View v) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void readBlockedTaskFile() {
        try {
            FileInputStream fileInputStream = openFileInput("blockedTask.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            String[] fields = new String[1];
            while ((lines = bufferedReader.readLine()) != null) {
                fields = lines.split(";");
                for (String cellsOfLines : fields) {
                    String[] cells = cellsOfLines.split(",");
                    Log.d("MainActivity", Arrays.toString(cells));
                    Controller aController = (Controller) getApplicationContext();
                    aController.createBlockTask(cells[0], new Time(Integer.parseInt(cells[1]), Integer.parseInt(cells[2])), new Time(Integer.parseInt(cells[3]), Integer.parseInt(cells[3])), false, LocalDate.of(Integer.parseInt(cells[4]), Integer.parseInt(cells[5]), Integer.parseInt(cells[6])));
                    Log.d("MainActivity", "recreated old blocked task");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readTaskFile() {
        try {
            FileInputStream fileInputStream = openFileInput("task.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            String[] fields = new String[1];
            while ((lines = bufferedReader.readLine()) != null) {
                fields = lines.split(";");
                for (String cellsOfLines : fields) {
                    String[] cells = cellsOfLines.split(",");
                    Log.d("MainActivity", Arrays.toString(cells));
                    Controller aController = (Controller) getApplicationContext();
                    aController.createTask(cells[0], Double.parseDouble(cells[1]), Integer.parseInt(cells[2]), false, LocalDate.of(Integer.parseInt(cells[3]), Integer.parseInt(cells[4]), Integer.parseInt(cells[5])));
                    Log.d("MainActivity", "recreated old task");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
