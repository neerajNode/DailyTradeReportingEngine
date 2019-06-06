package com.reporting.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.reporting.models.Instruction;

//class to generate sample instructions
public class InputDataGenerator {

	/**
	 * Generate a list of instructions and return the list as an Optional
	 * @return
	 */
	public static Optional<List<Instruction>> generate() {

		try {
			List<Instruction> list = new ArrayList<>();
			list.add(new Instruction("foo1","B",0.50,"AED","07 Jun 2019","08 Jun 2019",100,100.25));
			list.add(new Instruction("bar1","S",0.70,"INR","01 Jan 2019","03 Jan 2019",200,90.25));
			list.add(new Instruction("foo2","S",0.60,"GBP","01 May 2019","02 May 2019",100,100.25));
			list.add(new Instruction("bar2","B",0.70,"SAR","01 Jan 2016","02 Jan 2016",200,90.25));
			list.add(new Instruction("foo3","B",0.50,"GBP","09 May 2018","10 May 2018",100,100.25));
			list.add(new Instruction("bar3","S",0.99,"INR","01 Jan 2019","03 Jan 2019",200,90.25));
			list.add(new Instruction("foo4","B",0.80,"GBP","07 Jun 2019","08 Jun 2019",100,100.25));
			list.add(new Instruction("bar4","S",0.90,"INR","01 Feb 2019","03 Feb 2019",200,90.25));
			list.add(new Instruction("foo5","B",0.55,"GBP","07 Jun 2019","08 Jun 2019",100,100.25));
			list.add(new Instruction("bar5","S",0.98,"INR","16 Jun 2019","17 Jun 2019",200,90.25));
			
			return Optional.of(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}

	}
}
