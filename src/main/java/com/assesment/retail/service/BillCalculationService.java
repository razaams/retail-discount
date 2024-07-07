package com.assesment.retail.service;

import org.springframework.stereotype.Component;

import com.assesment.retail.discount.AmountDiscountContext;
import com.assesment.retail.discount.CustomerDiscountContext;
import com.assesment.retail.domain.Bill;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BillCalculationService {

	private CustomerDiscountContext customerDiscountContext;

	private AmountDiscountContext amountDiscountContext;

	public Bill calculatePayableBill(Bill bill) {

		validateBill(bill);

		double discount = customerDiscountContext.applyDiscount(bill) + amountDiscountContext.applyDiscount(bill);

		bill.setDiscount(discount);

		bill.setPayableAmount(Math.max(bill.getTotal() - bill.getDiscount(), 0));

		return bill;
	}

	public void validateBill(Bill bill) {
		if (bill == null)
			throw new IllegalArgumentException("Bill cannot be null");

		if (bill.getUser() == null)
			throw new IllegalArgumentException("Bill user cannot be null");

		if (bill.getItems() == null || bill.getItems().isEmpty())
			throw new IllegalArgumentException("Bill items cannot be null or empty");
	}

}
