package com.reporting.utils;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import org.junit.Test;

import com.reporting.models.Instruction;
import com.reporting.models.InstructionFinances;

public class UtilsTest {

	@Test
	public void testGetParsedDate() throws Exception {
		final LocalDate testDate = LocalDate.of(2019, 6, 1);
		String dateToParse = "01 Jun 2019";
		assertEquals(Utils.getParsedDate(dateToParse), testDate);

	}
	
	@Test
	public void testDerivedSettlementDate() throws Exception {
		Instruction instructionArabic=new Instruction("foo1","B",0.50,"AED","07 Jun 2019","08 Jun 2019",100,100.25);
		Instruction instructionDefault=new Instruction("foo1","B",0.50,"GBP","07 Jun 2019","08 Jun 2019",100,100.25);
		assertEquals(LocalDate.of(2019, 6, 10),Utils.getDerivedSettlementDate(instructionDefault));
		assertEquals(LocalDate.of(2019, 6, 9),Utils.getDerivedSettlementDate(instructionArabic));

	}
	
	@Test
	public void testCalculateTradeAmount() throws Exception {
		InstructionFinances finances=new InstructionFinances(BigDecimal.valueOf(0.50),Currency.getInstance("GBP"),BigDecimal.valueOf(100.50),50);
		assertEquals(0,Utils.calculateTradeAmount(finances).compareTo(BigDecimal.valueOf(2512.50)));

	}

}
