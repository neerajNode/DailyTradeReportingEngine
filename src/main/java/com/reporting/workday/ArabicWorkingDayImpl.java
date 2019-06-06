package com.reporting.workday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/*
 * Implementation to check and return next working day(if applicable) where currency type is
 * AED and SGP
 */
public class ArabicWorkingDayImpl implements WorkingDay {

    
	//Map for the days to add for a given Day of the week
    Map<DayOfWeek, Integer> workingDayMap = new HashMap<>();

    //Singleton Implementation
    
    private static ArabicWorkingDayImpl instance = null;
    
    public static ArabicWorkingDayImpl getInstance() {
		if (instance == null) {
			instance = new ArabicWorkingDayImpl();
		}
		return instance;
	}

	private ArabicWorkingDayImpl() {
		super();
		this.populateWorkingDays();
	}
	
	private void populateWorkingDays() {
		this.workingDayMap.put(DayOfWeek.MONDAY, 0);
		this.workingDayMap.put(DayOfWeek.TUESDAY, 0);
		this.workingDayMap.put(DayOfWeek.WEDNESDAY, 0);
		this.workingDayMap.put(DayOfWeek.THURSDAY, 0);
		this.workingDayMap.put(DayOfWeek.FRIDAY, 2);
		this.workingDayMap.put(DayOfWeek.SATURDAY,1 );
		this.workingDayMap.put(DayOfWeek.SUNDAY, 0);
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
