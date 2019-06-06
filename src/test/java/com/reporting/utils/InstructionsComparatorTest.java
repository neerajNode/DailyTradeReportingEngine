package com.reporting.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.reporting.models.Instruction;

public class InstructionsComparatorTest {

	@Test
	public void testInstructionsComparator() throws Exception {
		Instruction instr1 = new Instruction("foo1", "B", 0.50, "AED", "07 Jun 2019", "08 Jun 2019", 100, 100.25);
		Instruction instr2 = new Instruction("bar1", "S", 0.50, "INR", "01 Jan 2019", "03 Jan 2019", 200, 100.25);
		InstructionsComparator comparator = new InstructionsComparator();
		assertEquals(-1, comparator.compare(instr1, instr2));
	}

}
