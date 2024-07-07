package com.assesment.retail.discount.strategy;

import org.springframework.stereotype.Component;

import com.assesment.retail.discount.BillAmountDiscount;
import com.assesment.retail.domain.Bill;

@Component
public class PerHundredAmountDiscountStrategy implements BillAmountDiscount {

	private static final double BULK_DISCOUNT_THRESHOLD = 100;

	private static final double BULK_DISCOUNT_AMOUNT = 5;

	@Override
	public double calculateDiscount(Bill bill) {
		return (bill.getTotal() / BULK_DISCOUNT_THRESHOLD) * BULK_DISCOUNT_AMOUNT;
	}

	@Override
	public boolean isApplicable(Bill bill) {
		return bill.getTotal() >= BULK_DISCOUNT_THRESHOLD;
	}

}
