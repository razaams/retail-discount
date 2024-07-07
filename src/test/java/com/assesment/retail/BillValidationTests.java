package com.assesment.retail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.assesment.retail.domain.Bill;
import com.assesment.retail.domain.Item;
import com.assesment.retail.domain.User;
import com.assesment.retail.service.BillCalculationService;

@SpringBootTest
public class BillValidationTests {

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
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
			Bill billWithNullUser = Bill.builder().user(null).items(Arrays.<Item> asList(new Item())).build();
			billCalculationService.calculatePayableBill(billWithNullUser);
		});

		assertEquals("Bill user cannot be null", ex.getMessage());
	}

	@Test
	void testValidateBill_NullItems() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
			Bill billWithNullItems = Bill.builder().user(new User()).items(null).build();
			billCalculationService.calculatePayableBill(billWithNullItems);
		});

		assertEquals("Bill items cannot be null or empty", ex.getMessage());
	}

	@Test
	void testValidateBill_EmptyItems() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
			Bill billWithEmptyItems = Bill.builder().user(new User()).items(Collections.<Item> emptyList()).build();
			billCalculationService.calculatePayableBill(billWithEmptyItems);
		});

		assertEquals("Bill items cannot be null or empty", ex.getMessage());
	}

}
