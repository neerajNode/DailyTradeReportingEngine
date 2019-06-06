package com.reporting.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import com.reporting.enums.InstructionTypeEnum;
import com.reporting.utils.Utils;
import com.reporting.workday.ArabicWorkingDayImpl;
import com.reporting.workday.DefaultWorkingDayImpl;

//Model Class to represent an input instruction
 public class Instruction {

	// A financial entity whose shares are to be bought or sold
	private final String entity;
	
	// Identifer for an outgoing or incoming instruction
	private final InstructionTypeEnum instructionType;
	
	// Date on which the instruction was sent by Client
	private final LocalDate instructionDate;
	
	// Date on which the client wished for the instruction to be settled 
	private final LocalDate instructedSettlementDate;
	
	// Derived settlement date in case instructedSettlementDate is not a valid working day
	private final LocalDate settlementDate;
	
	// Member variable of type InstructionFinances which encapsulates all finance related values of an instruction (agreedFxRate,currency,priceperunit,no.of units)
	private InstructionFinances instructionFinances;

	/**
	 * Constructor that takes raw input and instanties an Instruction Object
	 * @param entity
	 * @param instructionType
	 * @param agreedFx
	 * @param currency
	 * @param instructionDate
	 * @param instructedSettlementDate
	 * @param units
	 * @param pricePerUnit
	 * @throws Exception 
	 */
	public Instruction(
			String entity, 
			String instructionType,
			Double agreedFx,
			String currency, 
			String instructionDate,
			String instructedSettlementDate,
			Integer units,
			Double pricePerUnit
			) throws Exception 
	{
		super();
		
		//creates an object of InstructionFinances class
		this.instructionFinances =  new InstructionFinances(
		BigDecimal.valueOf(agreedFx), Currency.getInstance(currency), BigDecimal.valueOf(pricePerUnit),units);
		this.entity = entity;
		//get instruction type enum from instructionType string
		this.instructionType = InstructionTypeEnum.fromString(instructionType);
		//string to date conversion
		this.instructionDate = Utils.getParsedDate(instructionDate);
		//string to date conversion
		this.instructedSettlementDate = Utils.getParsedDate(instructedSettlementDate);
		//check and derive next working Settlement Date based on instructedSettlementDate
		this.settlementDate = Utils.getDerivedSettlementDate(this);
		
	}

	public InstructionFinances getInstructionFinances() {
		return instructionFinances;
	}

	public void setInstructionFinances(InstructionFinances instructionFinances) {
		this.instructionFinances = instructionFinances;
	}

	public String getEntity() {
		return entity;
	}

	public InstructionTypeEnum getInstructionType() {
		return instructionType;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public LocalDate getInstructedSettlementDate() {
		return instructedSettlementDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	

	@Override
	public String toString() {
		return "Instruction [entity=" + entity + ", instructionType=" + instructionType + ", instructionDate="
				+ instructionDate + ", instructedSettlementDate=" + instructedSettlementDate + ", settlementDate="
				+ settlementDate + ", instructionFinances=" + instructionFinances + "]";
	}
	
	

}
