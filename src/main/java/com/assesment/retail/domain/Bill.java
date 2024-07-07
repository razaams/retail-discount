package com.assesment.retail.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
	private List<Item> items;
	private User user;

	private double discount;
	private double payableAmount;

	public double getTotal() {
		return items.stream().mapToDouble(Item::getPrice).sum();
	}

	public double getNonGroceryTotal() {
		return items.stream().filter(item -> !item.isGrocery()).mapToDouble(Item::getPrice).sum();
	}
}
