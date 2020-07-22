package com.winestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.winestore.dto.CustomerDTO;
import com.winestore.dto.SaleDTO;
import com.winestore.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WineStoreController {

	private static final String CUSTOMERS_LIST = "http://www.mocky.io/v2/598b16291100004705515ec5";
	private static final String SALES_HISTORY = "http://www.mocky.io/v2/598b16861100004905515ec7";

	@Autowired
	private CustomerService customerService;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public void consume() {
		CustomerDTO[] customers = restTemplate(new RestTemplateBuilder()).getForObject(CUSTOMERS_LIST, CustomerDTO[].class);

		for (CustomerDTO dto : customers) {
			log.info("trying to save/update:".concat(dto.toString()));
			customerService.save(dto);
		}

		SaleDTO[] sales = restTemplate(new RestTemplateBuilder()).getForObject(SALES_HISTORY, SaleDTO[].class);

		for (SaleDTO sale : sales) {
			log.info(sale.toString());
		}

	}

}
