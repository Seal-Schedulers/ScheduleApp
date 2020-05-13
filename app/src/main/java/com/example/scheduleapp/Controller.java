package com.example.scheduleapp;

import android.app.Application;
import android.util.Log;

import com.example.scheduleapp.Day;
import com.example.scheduleapp.Time;
import com.example.scheduleapp.Task;

import java.time.LocalDate;
import java.util.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import com.opencsv.CSVWriter;

public class Controller extends Application{

	//File tasksFile = new File("src\\tasks.csv");
	//File schedulesFile = new File("src\\schedules.csv");
	//File blockedtasksFile = new File("src\\blockedtasks.csv");

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
	 * creates a task object using the Task class
	 * @param name
	 * @param hrs
	 * @param daysTillDue
	 * @
	 */
	public void createTask(String name, double hrs, int daysTillDue, boolean append)   {
		Log.d("Controller", "about to call LocalDate");
		LocalDate today = LocalDate.now();
		Log.d("Controller", "done with LocalDate");
		Task task = new Task(name, hrs, daysTillDue, reference, today);
		Log.d("Controller", "going to add to hashmap");
		tasks.put(reference, task);
		Log.d("Controller", "done adding to hashmap");
		addToDay(task);
		reference += 0.1;
	}

	public void createBlockTask(String name, Time start, Time end, boolean append)   {
		LocalDate today = LocalDate.now();
		Task task = new Task(name, start, end, reference, today);
		tasks.put(reference, task);
		blockTimeInDay(task);
		System.out.println(reference + " " + tasks.get(reference));
		reference += 0.1;
	}

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
		else {
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
			days.replace(task.getStartDate(), updatedDay);
		}
	}


	private void addToDay(Task task) {
		ArrayList<Double> fifteensPerDay = task.getFifteensPerDay();
		LocalDate startDate = task.getStartDate();
		int daysTillDue = (int) task.getDaysTillDue();
		Log.d("Controller", "getting ready to add to day");
		for (int d = 0; d <= daysTillDue; d++) {
			for (double numFifteens = 0; numFifteens < fifteensPerDay.get(d); numFifteens++) {
				if (days.containsKey(startDate)) {
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
	 * prints all the tasks in a day
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
					taskss[index] = tasks.get(day.getTaskKey(time)).toString();
				}
				break;
			}
			else if (day.containsKey(time)) {
				if (day.getTaskKey(time) == 0) {
					taskss[index] = "";
				}
				else {
					taskss[index] = tasks.get(day.getTaskKey(time)).toString();
				}
			}
			else {
				continue;
			}
			index++;
		}
		return taskss;
	}

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

	/*public void saveSchedule() {
		try {
			FileWriter outputfile = new FileWriter(schedulesFile, true);
			BufferedWriter br = new BufferedWriter(outputfile);
			// create CSVWriter object filewriter object as parameter
			CSVWriter writer = new CSVWriter(br);
			for (LocalDate date: days.keySet()) {
				String[] scheduleData = new String[99];
				scheduleData[0] = Integer.toString(date.getYear());
				scheduleData[1] = Integer.toString(date.getMonthValue());
				scheduleData[2] = Integer.toString(date.getDayOfMonth());
				Day day = days.get(date);
				int index = 3;
				for (Time time : Day.allTimes) {
					if (time.equals(new Time(23, 45))) {
						scheduleData[index] = Double.toString(day.getTaskKey(time));
						break;
					}
					else if (day.containsKey(time)) {
						scheduleData[index] = Double.toString(day.getTaskKey(time));        			}
					index++;
				}
				writer.writeNext(scheduleData);
			}
			// closing writer connection
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*public void recreate() throws IOException {
		recreateBlockedTasks();
		recreateTasks();
		recreateSchedule();
	}

	private void recreateTasks() throws IOException {
		FileReader fr = new FileReader("src\\tasks.csv");
		BufferedReader br = new BufferedReader(fr);
		String taskData = "";

        try {
        	br.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}

	private void recreateBlockedTasks() throws IOException {
		FileReader fr = new FileReader("src\\blockedtasks.csv");
		BufferedReader br = new BufferedReader(fr);
		String blockedTaskData = "";

        try {
        	br.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        FileWriter fw = new FileWriter("src\\blockedtasks.csv", false);
        fw.close();

	}

	private void recreateSchedule() throws IOException {
		FileReader fr = new FileReader("src\\schedules.csv");
		BufferedReader br = new BufferedReader(fr);
		String schedule = "";

        try {
        	br.readLine();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}*/

}
;