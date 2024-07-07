package com.assesment.retail.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {
	private UserType userType;
	private LocalDate customerSince;
}
