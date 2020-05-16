package com.example.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.scheduleapp.TasksActivity;

public class RemoveActivity extends AppCompatActivity {

    private static final String TAG = "RemoveActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        Log.d(TAG, "onCreate: called");
    }

    /**
     * removes the tasks from the task list in the TasksActivity and
     * returns to the TasksActivity
     * @param v
     */
    public void yesButton (View v) {
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        TasksActivity.list.remove(position);
        TasksActivity.adapter.notifyDataSetChanged();

        Intent newIntent = new Intent(this, TasksActivity.class);
        startActivity(newIntent);
    }

    /**
     * goes to the TasksActivity
     * @param v
     */
    public void noButton (View v) {
        Intent intent = new Intent(this, TasksActivity.class);
        startActivity(intent);
    }
}
