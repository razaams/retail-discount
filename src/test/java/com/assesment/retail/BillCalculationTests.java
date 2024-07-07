package com.assesment.retail;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.assesment.retail.domain.Bill;
import com.assesment.retail.domain.Item;
import com.assesment.retail.domain.User;
import com.assesment.retail.domain.UserType;
import com.assesment.retail.service.BillCalculationService;

@SpringBootTest
class BillCalculationTests {

	@Autowired
	private BillCalculationService billCalculationService;

	@Test
	void testEmployeeBill() {

		User employee = new User(UserType.EMPLOYEE, LocalDate.now());
		Bill employeeBill = Bill.builder().user(employee).items(Arrays.<Item> asList(new Item("Room cooler", 40, false),
				new Item("Air condition", 90, false), new Item("Chair", 70, true))).build();

		assertEquals(151, billCalculationService.calculatePayableBill(employeeBill).getPayableAmount());
	}

	@Test
	void testAffiliateBill() {
		User affiliate = new User(UserType.AFFILIATE, LocalDate.now());
		Bill affiliateBill = Bill.builder().user(affiliate)
				.items(Arrays.<Item> asList(new Item("Room cooler", 40, false), new Item("Air condition", 90, false),
						new Item("Chair", 70, true)))
				.build();

		assertEquals(177, billCalculationService.calculatePayableBill(affiliateBill).getPayableAmount());
	}

	@Test
	void testNotLoyalCustomerBill() {
		User newCustomer = new User(UserType.CUSTOMER, LocalDate.now());
		Bill newCustomerBill = Bill.builder().user(newCustomer)
				.items(Arrays.<Item> asList(new Item("Room cooler", 40, false), new Item("Air condition", 90, false),
						new Item("Chair", 70, true)))
				.build();

		assertEquals(190, billCalculationService.calculatePayableBill(newCustomerBill).getPayableAmount());
	}

	@Test
	void testLoyalCustomerBill() {
		User loyalCustomer = new User(UserType.CUSTOMER, LocalDate.now().minusYears(3));
		Bill loyalCustomerBill = Bill.builder().user(loyalCustomer)
				.items(Arrays.<Item> asList(new Item("Room cooler", 40, false), new Item("Air condition", 90, false),
						new Item("Chair", 70, true)))
				.build();

		assertEquals(183.5, billCalculationService.calculatePayableBill(loyalCustomerBill).getPayableAmount());
	}
}
