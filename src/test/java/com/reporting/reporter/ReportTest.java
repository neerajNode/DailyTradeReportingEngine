package com.reporting.reporter;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.reporting.enums.InstructionTypeEnum;
import com.reporting.models.Instruction;

public class ReportTest {

	private List<Instruction> list;
	private Report report;

	@Before
	public void setUp() throws Exception {
		list=new ArrayList<>();
		list.add(new Instruction("foo1","B",0.50,"AED","07 Jun 2019","08 Jun 2019",100,100.25));
		list.add(new Instruction("bar1","S",0.70,"INR","01 Jan 2019","03 Jan 2019",200,90.25));
		list.add(new Instruction("foo2","S",0.60,"GBP","01 May 2019","02 May 2019",100,100.25));
		list.add(new Instruction("bar2","B",0.70,"SAR","01 Jan 2016","02 Jan 2016",200,90.25));
		report = new Report(list);
	}

	@Test
	public void testGetDailyReport() throws Exception {
		final Map<LocalDate, BigDecimal> settlementReportsMap = report.getDailyReport(InstructionTypeEnum.BUY); 
		assertEquals(2, settlementReportsMap.size());
		assertTrue(settlementReportsMap.get(LocalDate.of(2019, 6, 9)).compareTo(new BigDecimal(5012.5)) == 0);
	}
	
	@Test
	public void testGetRanking() throws Exception {
		final Map<LocalDate, List<Instruction>> settlementReportsMap = report.getRanking(InstructionTypeEnum.BUY); 
		assertEquals(2, settlementReportsMap.size());
		assertEquals(1, settlementReportsMap.get(LocalDate.of(2019, 6, 9)).size());
	    assertTrue(settlementReportsMap.get(LocalDate.of(2016, 1, 3)).get(0).getEntity().equals("bar2"));
	}

}
