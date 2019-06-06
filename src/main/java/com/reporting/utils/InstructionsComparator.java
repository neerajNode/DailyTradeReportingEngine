package com.reporting.utils;

import java.util.Comparator;

import com.reporting.models.Instruction;

//Comparator to compare trade amounts of 2 instructions
public class InstructionsComparator implements Comparator<Instruction> {
	
	@Override
	public int compare(Instruction arg0, Instruction arg1) {
		return arg0.getInstructionFinances().getTradeAmount().compareTo(arg1.getInstructionFinances().getTradeAmount());
	}
}
