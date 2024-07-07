package com.assesment.retail.discount;

import java.util.List;

import org.springframework.stereotype.Component;

import com.assesment.retail.domain.Bill;

@Component
public class AmountDiscountContext {

	private List<AmountDiscount> amountDiscountStrategies;

	public AmountDiscountContext(List<AmountDiscount> amountDiscountStrategies) {
		this.amountDiscountStrategies = amountDiscountStrategies;
	}

	public double applyDiscount(Bill bill) {
		return amountDiscountStrategies.stream().filter(strategy -> strategy.isApplicable(bill)).findFirst()
				.map(strategy -> strategy.calculateDiscount(bill)).orElse(0d);
	}
}
