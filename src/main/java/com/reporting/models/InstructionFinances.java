package com.reporting.models;

import java.math.BigDecimal;
import java.util.Currency;

import com.reporting.utils.Utils;

//Model Class to represent finance related values of an instruction
public class InstructionFinances {

	private final BigDecimal agreedFxRate;
	private final Currency currency;
	private final BigDecimal perUnitPrice;
	private final int units;
	private final BigDecimal tradeAmount;

	public InstructionFinances(BigDecimal agreedFxRate, Currency currency, BigDecimal perUnitPrice, int units) throws Exception {
		
		this.agreedFxRate = agreedFxRate;
		this.currency = currency;
		this.perUnitPrice = perUnitPrice;
		this.units = units;
		//calculate trade amount
		this.tradeAmount = Utils.calculateTradeAmount(this);
	}

	public BigDecimal getAgreedFxRate() {
		return agreedFxRate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public BigDecimal getPerUnitPrice() {
		return perUnitPrice;
	}

	public int getUnits() {
		return units;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}

	@Override
	public String toString() {
		return "InstructionFinances [agreedFxRate=" + agreedFxRate + ", currency=" + currency + ", perUnitPrice="
				+ perUnitPrice + ", units=" + units + ", tradeAmount=" + tradeAmount + "]";
	}
	
}
