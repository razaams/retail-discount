package com.assesment.retail.discount.strategy;

import org.springframework.stereotype.Component;

import com.assesment.retail.discount.CustomerDiscount;
import com.assesment.retail.domain.Bill;
import com.assesment.retail.domain.User;
import com.assesment.retail.domain.UserType;

@Component
public class AffiliateDiscountStrategy implements CustomerDiscount {

	private static final double DISCOUNT_PERCENTAGE = 10;

	@Override
	public double calculateDiscount(Bill bill) {
		return (DISCOUNT_PERCENTAGE / 100) * bill.getNonGroceryTotal();
	}

	@Override
	public boolean isApplicable(User user) {
		return UserType.AFFILIATE.equals(user.getUserType());
	}
}
