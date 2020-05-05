package com.example.scheduleapp;

import android.os.Parcel;
import android.os.Parcelable;

public class TaskDisplay implements Parcelable {
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

    protected TaskDisplay(Parcel in) {
        taskName = in.readString();
        hours = in.readString();
        days = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(taskName);
        dest.writeString(hours);
        dest.writeString(days);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TaskDisplay> CREATOR = new Parcelable.Creator<TaskDisplay>() {
        @Override
        public TaskDisplay createFromParcel(Parcel in) {
            return new TaskDisplay(in);
        }

        @Override
        public TaskDisplay[] newArray(int size) {
            return new TaskDisplay[size];
        }
    };
}