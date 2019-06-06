package com.reporting.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class InstructionTypeEnumTest {

	@Test
	public void testInstructionTypeEnum() {
		assertEquals(InstructionTypeEnum.BUY,InstructionTypeEnum.fromString("B"));
		assertEquals(InstructionTypeEnum.SELL,InstructionTypeEnum.fromString("S"));
	}

}
