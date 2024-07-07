package com.assesment.retail;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.assesment.retail.discount.BillAmountDiscount;
import com.assesment.retail.discount.strategy.PerHundredAmountDiscountStrategy;
import com.assesment.retail.domain.Bill;
import com.assesment.retail.domain.Item;
import com.assesment.retail.domain.User;
import com.assesment.retail.domain.UserType;

@SpringBootTest
class BillAmountDiscountStrategyApplicabilityTests {

	@Test
	void testBillAmountOver_100_Applicable() {

		User customer = new User(UserType.CUSTOMER, LocalDate.now());
		Bill bill = Bill.builder().user(customer).items(
				Arrays.asList(new Item("Apple", 50, true), new Item("Banana", 50, true), new Item("Chair", 90, false)))
				.build();

		BillAmountDiscount perHundredBillAmountDiscountStrategy = new PerHundredAmountDiscountStrategy();

		assertTrue(perHundredBillAmountDiscountStrategy.isApplicable(bill));
	}

	@Test
	void testBillAmountBelow_100_Applicable() {
		User customer = new User(UserType.CUSTOMER, LocalDate.now());
		Bill bill = Bill.builder().user(customer).items(
				Arrays.asList(new Item("Apple", 5, true), new Item("Banana", 3, true), new Item("Chair", 40, false)))
				.build();

		BillAmountDiscount perHundredBillAmountDiscountStrategy = new PerHundredAmountDiscountStrategy();

		assertFalse(perHundredBillAmountDiscountStrategy.isApplicable(bill));
	}
}
