package com.reporting.enums;

import java.util.Arrays;

//Enum class to hold type of Instructions B->Buy,S->Sell
public enum InstructionTypeEnum {
	BUY("B"), SELL("S");

	private String value;

	private InstructionTypeEnum(String value) {
		this.value = value;
	}
	
	/**
	 * returns InstructionTypeEnum from input string
	 * @param  str InstructionType String
	 * @return InstructionTypeEnum
	 * @throws IllegalArgumentException
	 */
	public static InstructionTypeEnum fromString(String str) throws IllegalArgumentException {
        return Arrays.stream(InstructionTypeEnum.values())
                .filter(type -> type.value.equals(str))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown Instruction Type: " + str));
        }
}
