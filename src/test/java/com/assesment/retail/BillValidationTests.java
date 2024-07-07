package com.assesment.retail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.assesment.retail.domain.Bill;
import com.assesment.retail.domain.Item;
import com.assesment.retail.domain.User;
import com.assesment.retail.domain.UserType;
import com.assesment.retail.service.BillCalculationService;

@SpringBootTest
class BillValidationTests {

	@Autowired
	private BillCalculationService billCalculationService;

	@Test
	void testValidateBill_NullBill() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
			billCalculationService.calculatePayableBill(null);
		});

		assertEquals("Bill cannot be null", ex.getMessage());
	}

	@Test
	void testValidateBill_NullUser() {
		Bill billWithNullUser = Bill.builder().user(null).items(Arrays.<Item> asList(new Item())).build();
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
			billCalculationService.calculatePayableBill(billWithNullUser);
		});

		assertEquals("Bill user cannot be null", ex.getMessage());
	}

	@Test
	void testValidateBill_User_NullUserType() {
		Bill billWithNullUserType = Bill.builder().user(new User()).items(Arrays.<Item> asList(new Item())).build();
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
			billCalculationService.calculatePayableBill(billWithNullUserType);
		});

		assertEquals("Bill user type cannot be null", ex.getMessage());
	}

	@Test
	void testValidateBill_User_NullCustomerSince() {

		Bill billWithNullCustomerSince = Bill.builder().user(new User(UserType.CUSTOMER, null))
				.items(Arrays.<Item> asList(new Item())).build();
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
			billCalculationService.calculatePayableBill(billWithNullCustomerSince);
		});

		assertEquals("Bill user customer since cannot be null", ex.getMessage());
	}

	@Test
	void testValidateBill_NullItems() {
		Bill billWithNullItems = Bill.builder().user(new User(UserType.CUSTOMER, LocalDate.now())).items(null).build();
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
			billCalculationService.calculatePayableBill(billWithNullItems);
		});

		assertEquals("Bill items cannot be null", ex.getMessage());
	}

	@Test
	void testValidateBill_EmptyItems() {
		Bill billWithEmptyItems = Bill.builder().user(new User(UserType.CUSTOMER, LocalDate.now()))
				.items(Collections.<Item> emptyList()).build();
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
			billCalculationService.calculatePayableBill(billWithEmptyItems);
		});

		assertEquals("Bill items cannot be empty", ex.getMessage());
	}

	@Test
	void testBill_getTotal() {
		Bill bill = Bill.builder()
				.items(Arrays.<Item> asList(new Item("Room cooler", 40, false), new Item("Air condition", 90, true)))
				.build();

		assertEquals(130, bill.getTotal());
	}

	@Test
	void testBill_getNonGroceryTotal() {
		Bill bill = Bill.builder().items(Arrays.<Item> asList(new Item("Room cooler", 40, false),
				new Item("Air condition", 90, true), new Item("Chair", 30, true))).build();

		assertEquals(40, bill.getNonGroceryTotal());
	}

}
