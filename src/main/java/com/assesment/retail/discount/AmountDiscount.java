package com.assesment.retail.discount;

import com.assesment.retail.domain.Bill;

public interface AmountDiscount {

	double calculateDiscount(Bill bill);

	boolean isApplicable(Bill bill);
}
