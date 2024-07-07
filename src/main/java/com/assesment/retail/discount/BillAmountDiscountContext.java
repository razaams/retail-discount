package com.assesment.retail.discount;

import java.util.List;

import org.springframework.stereotype.Component;

import com.assesment.retail.domain.Bill;

@Component
public class BillAmountDiscountContext {

	private List<BillAmountDiscount> amountDiscountStrategies;

	public BillAmountDiscountContext(List<BillAmountDiscount> amountDiscountStrategies) {
		this.amountDiscountStrategies = amountDiscountStrategies;
	}

	public double applyDiscount(Bill bill) {
		return amountDiscountStrategies.stream().filter(strategy -> strategy.isApplicable(bill)).findFirst()
				.map(strategy -> strategy.calculateDiscount(bill)).orElse(0d);
	}
}
