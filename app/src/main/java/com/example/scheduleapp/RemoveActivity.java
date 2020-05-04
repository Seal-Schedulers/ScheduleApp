package com.example.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RemoveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
    }

    public void removeButton (View v) {
        EditText removeInt = findViewById(R.id.inputTaskNumber);
        String removeIntStr = removeInt.getText().toString();

        Intent intent = new Intent(this, CreateActivity.class);
        intent.putExtra("removeInt", removeIntStr);
    }


}
