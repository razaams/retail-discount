package com.assesment.retail;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.assesment.retail.discount.CustomerDiscount;
import com.assesment.retail.discount.strategy.AffiliateDiscountStrategy;
import com.assesment.retail.discount.strategy.EmployeeDiscountStrategy;
import com.assesment.retail.discount.strategy.LoyaltyDiscountStrategy;
import com.assesment.retail.domain.User;
import com.assesment.retail.domain.UserType;

@SpringBootTest
class CustomerDiscountStrategyApplicabilityTests {

	@Test
	void testEmployeeApplicable() {

		User employee = new User(UserType.EMPLOYEE, LocalDate.now());
		CustomerDiscount employeeDiscountStrategy = new EmployeeDiscountStrategy();

		assertTrue(employeeDiscountStrategy.isApplicable(employee));
	}

	@Test
	void testAffiliateApplicable() {
		User affiliate = new User(UserType.AFFILIATE, LocalDate.now());
		CustomerDiscount affiliateDiscountStrategy = new AffiliateDiscountStrategy();

		assertTrue(affiliateDiscountStrategy.isApplicable(affiliate));
	}

	@Test
	void testLoyalCustomerApplicable() {
		User loyalCustomer = new User(UserType.CUSTOMER, LocalDate.now().minusYears(3));
		CustomerDiscount loyaltyDiscountStrategy = new LoyaltyDiscountStrategy();

		assertTrue(loyaltyDiscountStrategy.isApplicable(loyalCustomer));
	}

	@Test
	void testNotLoyalCustomerApplicable() {
		User loyalCustomer = new User(UserType.CUSTOMER, LocalDate.now());
		CustomerDiscount loyaltyDiscountStrategy = new LoyaltyDiscountStrategy();

		assertFalse(loyaltyDiscountStrategy.isApplicable(loyalCustomer));
	}
}
