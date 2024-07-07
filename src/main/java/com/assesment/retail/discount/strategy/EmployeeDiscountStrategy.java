package com.assesment.retail.discount.strategy;

import org.springframework.stereotype.Component;

import com.assesment.retail.discount.CustomerDiscount;
import com.assesment.retail.domain.Bill;
import com.assesment.retail.domain.User;
import com.assesment.retail.domain.UserType;

@Component
public class EmployeeDiscountStrategy implements CustomerDiscount {

	private static final double DISCOUNT_PERCENTAGE = 30;

	@Override
	public double calculateDiscount(Bill bill) {
		return (DISCOUNT_PERCENTAGE / 100) * bill.getNonGroceryTotal();
	}

	@Override
	public boolean isApplicable(User user) {
		return UserType.EMPLOYEE.equals(user.getUserType());
	}
}
