package com.example.scheduleapp;

public class TaskDisplay {
    //Data
    private String taskName;
    private String hours;
    private String days;

    /**
     * constructs a taskDisplay object
     * @param taskName: the name of the task
     * @param hours: the total hours required for the task
     * @param days: the number of days until the task is due
     */
    public TaskDisplay(String taskName, String hours, String days) {
        this.taskName = taskName;
        this.hours = hours;
        this.days = days;
    }

    //getter and setter methods
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