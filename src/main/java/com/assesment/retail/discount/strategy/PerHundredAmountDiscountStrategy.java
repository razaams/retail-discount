package com.assesment.retail.discount.strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.assesment.retail.discount.AmountDiscount;
import com.assesment.retail.domain.Bill;

@Component
public class PerHundredAmountDiscountStrategy implements AmountDiscount {

	@Value("${discount.amount.threshold}")
	private double BULK_DISCOUNT_THRESHOLD;

	@Value("${discount.amount.hundred.percentage}")
	private double BULK_DISCOUNT_AMOUNT;

	@Override
	public double calculateDiscount(Bill bill) {
		return (bill.getTotal() / BULK_DISCOUNT_THRESHOLD) * BULK_DISCOUNT_AMOUNT;
	}

	@Override
	public boolean isApplicable(Bill bill) {
		return bill.getTotal() >= BULK_DISCOUNT_THRESHOLD;
	}

}
