package com.assesment.retail;

import java.time.LocalDate;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.assesment.retail.discount.CustomerDiscountContext;
import com.assesment.retail.domain.Bill;
import com.assesment.retail.domain.Item;
import com.assesment.retail.domain.User;
import com.assesment.retail.domain.UserType;

@SpringBootTest
class CustomerDiscountTests {

	@Autowired
	private CustomerDiscountContext customerDiscountContext;

	@Test
	void testEmployeeDiscount() {
		User employee = new User(UserType.EMPLOYEE, LocalDate.now());
		Bill employeeBill = Bill.builder().user(employee).items(Arrays.<Item> asList(new Item("Room cooler", 40, false),
				new Item("Air condition", 90, false), new Item("Chair", 70, true))).build();

		assertEquals(39, customerDiscountContext.applyDiscount(employeeBill));
	}

	@Test
	void testAffiliateDiscount() {
		User affiliate = new User(UserType.AFFILIATE, LocalDate.now());
		Bill affiliateBill = Bill.builder().user(affiliate)
				.items(Arrays.<Item> asList(new Item("Room cooler", 40, false), new Item("Air condition", 90, false),
						new Item("Chair", 70, true)))
				.build();

		assertEquals(13, customerDiscountContext.applyDiscount(affiliateBill));
	}

	@Test
	void testNewCustomerDiscount() {
		User newCustomer = new User(UserType.CUSTOMER, LocalDate.now());
		Bill newCustomerBill = Bill.builder().user(newCustomer)
				.items(Arrays.<Item> asList(new Item("Room cooler", 40, false), new Item("Air condition", 90, false),
						new Item("Chair", 70, true)))
				.build();

		assertEquals(0, customerDiscountContext.applyDiscount(newCustomerBill));
	}

	@Test
	void testLoyalCustomerDiscount() {
		User loyalCustomer = new User(UserType.CUSTOMER, LocalDate.now().minusYears(3));
		Bill loyalCustomerBill = Bill.builder().user(loyalCustomer)
				.items(Arrays.<Item> asList(new Item("Room cooler", 40, false), new Item("Air condition", 90, false),
						new Item("Chair", 70, true)))
				.build();

		assertEquals(6.5, customerDiscountContext.applyDiscount(loyalCustomerBill));
	}
}
