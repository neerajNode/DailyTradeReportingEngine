package com.reporting.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.reporting.models.Instruction;
import com.reporting.models.InstructionFinances;
import com.reporting.workday.ArabicWorkingDayImpl;
import com.reporting.workday.DefaultWorkingDayImpl;

//Common Utility Class
public class Utils {

	//Date formatter to match input date string format eg: 07 Jun 2019
	final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

	/**
	 * Utility method to parse string and return LocalDate
	 * @param date Date string eg: 07 Jun 2019
	 * @return LocalDate object
	 * @throws Exception
	 */
	public static LocalDate getParsedDate(String date) throws Exception {
		return LocalDate.parse(date, formatter);
	}

	/**
	 * Utility method to check if a given date is a working day and in case not return next working day based on
	 * currency type 
	 * @param instruction object containing settlement date
	 * @return LocalDate object
	 * @throws Exception
	 */
	public static LocalDate getDerivedSettlementDate(Instruction instruction) throws Exception {
		if (instruction.getInstructionFinances().getCurrency().toString().equals("AED")
				|| instruction.getInstructionFinances().getCurrency().toString().equals("SAR")) {
			return ArabicWorkingDayImpl.getInstance().getWorkingDay(instruction.getInstructedSettlementDate());
		} else
			return DefaultWorkingDayImpl.getInstance().getWorkingDay(instruction.getInstructedSettlementDate());
	}
    
	/**
	 * Calculate and return Trade amount of passed instruction
	 * using USD amount of a trade = Price per unit * Units * Agreed Fx
	 * @param finance object containing instruction finances
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal calculateTradeAmount(InstructionFinances finance) throws Exception {
		return finance.getPerUnitPrice().multiply(BigDecimal.valueOf(finance.getUnits()))
				.multiply(finance.getAgreedFxRate());
	}
}
