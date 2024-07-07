package com.assesment.retail;

import java.io.File;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.assesment.retail.domain.Bill;
import com.assesment.retail.service.BillCalculationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
@Profile("!test")
public class RetailDiscountCommandLineRunner implements CommandLineRunner {

	private BillCalculationService billCalculationService;

	@Override
	public void run(String... args) throws Exception {
		if (args.length != 1) {
			if (log.isInfoEnabled())
				log.info("Usage: java -jar retail-discount-0.0.1-SNAPSHOT.jar /path-to-your-json-file.json");
		}

		String filePath = args[0];

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		try {
			List<Bill> bills = objectMapper.readValue(new File(filePath), new TypeReference<List<Bill>>() {
			});

			bills.stream().forEach(bill -> {
				if (log.isInfoEnabled())
					log.info("===================================================");

				Bill customerBill = billCalculationService.calculatePayableBill(bill);

				if (log.isInfoEnabled())
					log.info("{}", customerBill);
			});

		} catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error(ex.getMessage());
		}
	}
}
