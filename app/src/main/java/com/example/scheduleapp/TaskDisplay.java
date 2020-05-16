package com.example.scheduleapp;

import android.os.Parcel;
import android.os.Parcelable;

public class TaskDisplay {
    private String taskName;
    private String hours;
    private String days;

    public TaskDisplay(String taskName, String hours, String days) {
        this.taskName = taskName;
        this.hours = hours;
        this.days = days;
    }


    public String getTaskName() {
        return taskName;
    }

    public String getHours() {
        return hours;
    }

    public String getDays() {
        return days;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setDays(String days) {
        this.days = days;
    }
}