package com.example.scheduleapp;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.*;
import java.io.IOException;

public class Controller extends Application{

	// Data
	private HashMap<Double, Task> tasks;
	private HashMap<LocalDate, Day> days;
	private static double reference = 0.1;

	// Constructors
	public Controller() {
		super();
		tasks = new HashMap<Double, Task>();
		days = new HashMap<LocalDate, Day>();
	}

	// Methods
	public HashMap<LocalDate, Day> getDaysHashMap(){
		return days;
	}

	/**
	 * creates a task object using the Task class,
	 * adds it to the tasks map, and adds to the appropriate days
	 * @param name
	 * @param hrs
	 * @param daysTillDue
	 * @param append
	 * @param date
	 */
	public void createTask(String name, double hrs, int daysTillDue, boolean append, LocalDate date)   {
		Log.d("Controller", "about to call LocalDate");
		LocalDate today = date;
		Log.d("Controller", "done with LocalDate");
		Task task = new Task(name, hrs, daysTillDue, reference, today);
		Log.d("Controller", "going to add to hashmap");
		tasks.put(reference, task);
		Log.d("Controller", "done adding to hashmap");
		addToDay(task);
		if (append) {
			String taskToSave = name + "," + hrs + "," + daysTillDue + "," + today.getYear() + "," + today.getMonthValue() + "," + today.getDayOfMonth();
			Log.d("writing", taskToSave);

			try {
				FileOutputStream fileOutputStream = openFileOutput("task.csv", MODE_APPEND);
				fileOutputStream.write(taskToSave.getBytes());
				fileOutputStream.write(";".getBytes());
				fileOutputStream.close();

				Toast.makeText(getApplicationContext(), "Task Saved", Toast.LENGTH_SHORT).show();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		reference += 0.1;
	}

	/**
	 * creates a blocked task object using the Task class,
	 * adds it to the tasks map, and adds to the appropriate day
	 * @param name
	 * @param start
	 * @param end
	 * @param append
	 * @param date
	 */
	public void createBlockTask(String name, Time start, Time end, boolean append, LocalDate date)   {
		LocalDate today = date;
		Task task = new Task(name, start, end, reference, today);
		tasks.put(reference, task);
		blockTimeInDay(task);
		//System.out.println(reference + " " + tasks.get(reference));
		if(append) {
			String blockTaskToSave = name + "," + start.getHour() + "," + start.getMinute() + "," + end.getHour() + "," + end.getMinute() + "," + today.getYear() + "," + today.getMonthValue() + "," + today.getDayOfMonth();
			Log.d("writing", blockTaskToSave);

			try {
				FileOutputStream fileOutputStream = openFileOutput("blockedTask.csv", MODE_APPEND);
				fileOutputStream.write(blockTaskToSave.getBytes());
				fileOutputStream.write(";".getBytes());
				fileOutputStream.close();

				Toast.makeText(getApplicationContext(), "Blocked Task Saved", Toast.LENGTH_SHORT).show();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		reference += 0.1;
	}

	/**
	 * inputs the block task key into the time slot for its specific day
	 * @param task
	 */
	private void blockTimeInDay(Task task) {
		if(!days.containsKey(task.getStartDate())) {
			Day day = new Day();
			Time time = new Time(task.getStart());
			time.increment();
			for (Time t: Day.allTimes) {
				if (t.equals(task.getEnd())) {
					day.replace(t, task.getKey());
					break;
				}
				else if(t.equals(time)) {
					day.replace(t, task.getKey());
					time.increment();
				}
			}
			days.put(task.getStartDate(), day);
		}
		/*else {
			Day day = days.get(task.getStartDate());
			Day updatedDay = new Day();
			Time time = new Time(task.getStart());
			for (Time t: Day.allTimes) {
				if (t.equals(task.getEnd())) {
					updatedDay.replace(t, task.getKey());
					break;
				}
				else if(t.equals(time)) {
					updatedDay.replace(t, task.getKey());
					time.increment();
				}
			}
			/*Time index = new Time();
			for (Time t : Day.allTimes) {
//				System.out.println(t + ", " + index + " " + day);
				if (updatedDay.getTaskKey(t) == 0) {
					if (index.equals(new Time(23, 45))) {
						updatedDay.replace(t, day.getTaskKey(new Time(index)));
						break;
					}
					else {
					//System.out.println("day " + day.getTaskKey(index));
						updatedDay.replace(t, day.getTaskKey(new Time (index)));
						//System.out.println("updated " + updatedDay.getTaskKey(t));
					}
					index.increment();
				}
			}*/
			/*days.replace(task.getStartDate(), updatedDay);
		}*/
	}

	/**
	 * inputs the task key into the appropriate days based on
	 * the time allotted per day
	 * @param task
	 */
	private void addToDay(Task task) {
		ArrayList<Double> fifteensPerDay = task.getFifteensPerDay();
		LocalDate startDate = task.getStartDate();
		Log.d("Controller", "in method" + startDate.toString());
		int daysTillDue = (int) task.getDaysTillDue();
		Log.d("Controller", "getting ready to add to day");
		for (int d = 0; d <= daysTillDue; d++) {
			for (double numFifteens = 0; numFifteens < fifteensPerDay.get(d); numFifteens++) {
				if (days.containsKey(startDate)) {
					Log.d("Controller", "in the loop" + startDate.toString());
					Day day = days.get(startDate);
					for(Time t : Day.allTimes) {
						if (day.getTaskKey(t) == 0.0) {
							day.replace(t, task.getKey());
							days.replace(startDate, day);
							days.replace(startDate, priorityReschedule(startDate));
							break;
						}
					}
				}
				else {
					Day day = new Day();
					for(Time t : Day.allTimes) {
						if (day.getTaskKey(t) == 0.0) {
							day.replace(t, task.getKey());
							break;
						}
					}
					days.put(startDate, day);
				}
			}
			startDate = startDate.plusDays(1);
		}
		Log.d("Controller", "added to day");
	}

	/**
	 * creates a list with all the tasks and times in a day
	 * @param date
	 */
	public String[] getTaskFromDayList(LocalDate date) {
		Day day = days.get(date);
		String[] taskss = new String[day.getSize()];
		int index = 0;
		for (Time time : Day.allTimes) {
			if (time.equals(new Time(23, 45))) {
				if (day.getTaskKey(time) == 0) {
					taskss[index] = "";
				}
				else {
					taskss[index] = time.toString() + " - " + tasks.get(day.getTaskKey(time)).toString().toLowerCase();
				}
				break;
			}
			else if (day.containsKey(time)) {
				if (day.getTaskKey(time) == 0) {
					taskss[index] = "";
				}
				else {
					taskss[index] = time.toString() + " - " + tasks.get(day.getTaskKey(time)).toString().toLowerCase();
				}
			}
			else {
				continue;
			}
			index++;
		}
		return taskss;
	}

	/**
	 * sorts the schedule based on proximity of the due date using
	 * bubble sort
	 * @param today
	 */
	private Day priorityReschedule(LocalDate today) {
		Log.d("controller", "in priority schedule");
		Day day = days.get(today);
		boolean sorted = false;
		double temp;
		while(!sorted) {
			sorted = true;
			for (int i = 0; i < day.getSize() - 1; i++) {
				if (day.getTaskKey(Day.allTimes.get(i)) == 0.0 || day.getTaskKey(Day.allTimes.get(i+1)) == 0.0) {
					continue;
				}
				else if (tasks.get(day.getTaskKey(Day.allTimes.get(i))).getCurrentDaysTillDue(today) > tasks.get(day.getTaskKey(Day.allTimes.get(i+1))).getCurrentDaysTillDue(today)) {
					temp = day.getTaskKey(Day.allTimes.get(i));
					day.replace(Day.allTimes.get(i), day.getTaskKey(Day.allTimes.get(i+1)));
					day.replace(Day.allTimes.get(i+1), temp);
					sorted = false;
				}
			}
		}
		Log.d("controller", "out of priority schedule");
		return day;
	}
}
