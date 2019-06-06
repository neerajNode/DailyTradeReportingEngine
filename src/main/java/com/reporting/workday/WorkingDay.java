package com.reporting.workday;

import java.time.LocalDate;

//interface for classes that checks and returns a working day
public interface WorkingDay {

	LocalDate getWorkingDay(LocalDate date);
}
