package com.example.scheduleapp;

public class Time {

    //Data
    private int hr;
    private int min;

    //Constructors
    /**
     * constructs a Time object starting at 12 AM
     */
    public Time() {
        hr = 0;
        min = 0;
    }

    /**
     * constructs a Time object using hour and minutes
     * @param hr
     * @param min
     */
    public Time(int hr, int min) {
        this.hr = hr;
        this.min = min;
    }

    /**
     * constructs a Time object from another Time object
     * @param time
     */
    public Time(Time time) {
        this.hr = time.getHour();
        this.min = time.getMinute();
    }

    //getter methods
    public int getHour() {
        return hr;
    }

    public int getMinute() {
        return min;
    }

    /**
     * checks if the time parameter is equal to the Time object
     * @param time
     */
    public boolean equals(Time time) {
        return (time.getHour() == hr && time.getMinute() == min);
    }

    /**
     * increments the time by 15 minutes
     */
    public void increment() {
        if (min == 45) {
            if (hr == 23) {
                hr = 0;
            }
            else {
                hr++;
            }
            min = 0;
        }
        else {
            min += 15;
        }
    }

    /**
     * @return: the string of the time
     */
    public String toString() {
        String minutes = "";
        if (min < 15) {
            minutes = "00";
        }
        else {
            minutes = Integer.toString(min);
        }
        String hours = "";
        if (hr == 0) {
            hours = Integer.toString(hr+12);
        }
        else if (hr <= 12) {
            hours = Integer.toString(hr);
        }
        else {
            hours = Integer.toString(hr - 12);
        }
        String ending = "";
        if (hr < 12) {
            ending = " AM";
        }
        else {
            ending = " PM";
        }
        return hours + ":" + minutes + ending;
    }
}
