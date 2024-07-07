package com.assesment.retail.discount.strategy;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.assesment.retail.discount.CustomerDiscount;
import com.assesment.retail.domain.Bill;
import com.assesment.retail.domain.User;
import com.assesment.retail.domain.UserType;

@Component
public class LoyaltyDiscountStrategy implements CustomerDiscount {

	@Value("${discount.loyalty.percentage}")
	private double DISCOUNT_PERCENTAGE;

	@Override
	public double calculateDiscount(Bill bill) {
		return (DISCOUNT_PERCENTAGE / 100) * bill.getNonGroceryTotal();
	}

	@Override
	public boolean isApplicable(User user) {
		return UserType.CUSTOMER.equals(user.getUserType())
				&& user.getCustomerSince().isBefore(LocalDate.now().minusYears(2));
	}

}
