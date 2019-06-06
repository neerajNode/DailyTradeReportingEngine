package com.reporting.workday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/*
 * Default implementation to check and return next working day(if applicable) for all currency types excluding
 * AED and SGP
 */
public class DefaultWorkingDayImpl implements WorkingDay {

	//Map for the days to add for a given Day of the week
	Map<DayOfWeek, Integer> workingDayMap = new HashMap<>();

	
	//Singleton Implementation
	private static DefaultWorkingDayImpl instance = null;

	public static DefaultWorkingDayImpl getInstance() {
		if (instance == null) {
			instance = new DefaultWorkingDayImpl();
		}
		return instance;
	}

	private DefaultWorkingDayImpl() {
		super();
		this.populateWorkingDays();
	}

	
	private void populateWorkingDays() {
		this.workingDayMap.put(DayOfWeek.MONDAY, 0);
		this.workingDayMap.put(DayOfWeek.TUESDAY, 0);
		this.workingDayMap.put(DayOfWeek.WEDNESDAY, 0);
		this.workingDayMap.put(DayOfWeek.THURSDAY, 0);
		this.workingDayMap.put(DayOfWeek.FRIDAY, 0);
		this.workingDayMap.put(DayOfWeek.SATURDAY, 2);
		this.workingDayMap.put(DayOfWeek.SUNDAY, 1);
	}

	public LocalDate getWorkingDay(LocalDate date) {

		LocalDate workingDay;
		
		/*
		 * Map used to derive the number of days to add ,
		 * will be better that Switch case as it will work with O(1) complexity for all cases
		*/
		int daysToAdd = this.workingDayMap.get(date.getDayOfWeek());

		if (daysToAdd == 0)
			workingDay = date;
		else
			workingDay = date.plusDays(daysToAdd);

		return workingDay;
	}
}
