package com.assesment.retail.discount;

import java.util.List;

import org.springframework.stereotype.Component;

import com.assesment.retail.domain.Bill;

@Component
public class CustomerDiscountContext {

	private List<CustomerDiscount> customerDiscountStrategies;

	public CustomerDiscountContext(List<CustomerDiscount> customerDiscountStrategies) {
		this.customerDiscountStrategies = customerDiscountStrategies;
	}

	public double applyDiscount(Bill bill) {
		return customerDiscountStrategies.stream().filter(strategy -> strategy.isApplicable(bill.getUser())).findFirst()
				.map(strategy -> strategy.calculateDiscount(bill)).orElse(0d);
	}
}
