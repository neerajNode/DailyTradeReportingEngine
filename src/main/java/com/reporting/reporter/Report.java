package com.reporting.reporter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.reporting.enums.InstructionTypeEnum;
import com.reporting.models.Instruction;
import com.reporting.models.InstructionFinances;
import com.reporting.utils.InstructionsComparator;


public class Report {

	private List<Instruction> list;

	public Report(List<Instruction> list) {
		this.list = list;
	}
	//predicate to filter out outgoing instructions by checking InstructionTypeEnum value = BUY
	private final static Predicate<Instruction> outgoingInstructionsPredicate = Instruction -> Instruction
			.getInstructionType().equals(InstructionTypeEnum.BUY);

	//predicate to filter out incoming instructions by checking InstructionTypeEnum value = SELL
	private final static Predicate<Instruction> incomingInstructionsPredicate = Instruction -> Instruction
			.getInstructionType().equals(InstructionTypeEnum.SELL);

	/**
	 * Returns a map Settlement Date -> Total amount settled for that date
	 * @param instructionType (InstructionTypeEnum.Buy/Sell)
	 * @return
	 * @throws Exception
	 */
	public Map<LocalDate, BigDecimal> getDailyReport(InstructionTypeEnum instructionType) throws Exception {

		//decide the predicate to use by checking instructionType value
		final Predicate<Instruction> instructionPredicate = instructionType.equals(InstructionTypeEnum.BUY)
				? outgoingInstructionsPredicate : incomingInstructionsPredicate;
		
		/** Steps :
		 * 1.Filter the list of incoming instructions based on Instruction Type
		 * 2.Group the filtered instructions based on Settlement Date
		 * 3.For each group, find the sum of trade amounts of all instructions in that group
		 */
		Map<LocalDate, BigDecimal> settlementDateToTradeDoneMap = list.stream().filter(instructionPredicate)
				.collect(Collectors.groupingBy(Instruction::getSettlementDate,
						Collectors.mapping(Instruction::getInstructionFinances,
								Collectors.mapping(InstructionFinances::getTradeAmount,
										Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)))));

		return settlementDateToTradeDoneMap;
	}

	/**
	 * Method to print settlement report of a given instruction type (Buy/Sell)
	 * @param instructionType
	 * @throws Exception
	 */
	public void printSettlementReport(InstructionTypeEnum instructionType) throws Exception {
		String instructionTypeStr = instructionType.equals(InstructionTypeEnum.BUY) ? "Outgoing" : "Incoming";

		System.out.println(String.format("\n*** Settlement Report %s ***\n", instructionTypeStr));

		Map<LocalDate, BigDecimal> settlementReportsMap = getDailyReport(instructionType);

		settlementReportsMap.forEach((key, value) -> {
			System.out.println(String.format("Settlement Date: %s | Amount Settled: %s USD", key, value));

		});
	}

	/**
	 * Returns a map Settlement Date -> Sorted List of all instructions for that settlement date
	 * @param instructionType (InstructionTypeEnum.Buy/Sell)
	 * @return
	 * @throws Exception
	 */
	public Map<LocalDate, List<Instruction>> getRanking(InstructionTypeEnum instructionType) throws Exception {
		
		//decide the predicate to use by checking instructionType value
		final Predicate<Instruction> instructionPredicate = instructionType.equals(InstructionTypeEnum.BUY)
				? outgoingInstructionsPredicate : incomingInstructionsPredicate;
		
		//iterate the list of instructions and group them based on settlement date
		Map<LocalDate, List<Instruction>> settlementDateToInstructionsListMap = list.stream()
				.filter(instructionPredicate).collect(Collectors.groupingBy(Instruction::getSettlementDate));
		
		//iterate settlementDateToInstructionsListMap and sort the list of instructions based on Trade Amount
		settlementDateToInstructionsListMap.forEach((settlementDateKey, instructionsListValue) -> {
			instructionsListValue.sort(new InstructionsComparator().reversed());
		});

		return settlementDateToInstructionsListMap;
	}

	/**
	 * Method to print ranking report of a given instruction type (Buy/Sell)
	 * @param instructionType
	 * @throws Exception
	 */
	public void printRankings(InstructionTypeEnum instructionType) throws Exception {
		String instructionTypeStr = instructionType.equals(InstructionTypeEnum.BUY) ? "Outgoing" : "Incoming";

		System.out.println(String.format("\n*** Ranking Report %s ***", instructionTypeStr));
		Map<LocalDate, List<Instruction>> sortedInstructionsMap = getRanking(instructionType);
		sortedInstructionsMap.forEach((key, value) -> {
			System.out.println(String.format("\nSettlement Date: %s", key));
			AtomicInteger atomicInt = new AtomicInteger(0);
			value.forEach(instr -> {
				System.out.println(String.format("Rank:%s | Entity:%s | Amount:%s USD", atomicInt.addAndGet(1),
						instr.getEntity(), instr.getInstructionFinances().getTradeAmount()));
			});

		});
	}

}
