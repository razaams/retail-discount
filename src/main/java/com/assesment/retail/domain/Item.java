package com.assesment.retail.domain;

import lombok.Data;

@Data
public class Item {
	private String name;
	private double price;
	private boolean grocery;
}
