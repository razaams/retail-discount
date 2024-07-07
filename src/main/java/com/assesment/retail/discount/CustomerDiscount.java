package com.assesment.retail.discount;

import com.assesment.retail.domain.Bill;
import com.assesment.retail.domain.User;

public interface CustomerDiscount {
	double calculateDiscount(Bill bill);

	boolean isApplicable(User user);
}
