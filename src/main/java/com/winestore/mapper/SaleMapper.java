package com.winestore.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.winestore.domain.Customer;
import com.winestore.domain.Sale;
import com.winestore.dto.SaleDTO;

public class SaleMapper {

	public static Sale map(SaleDTO saleDTO, Customer customer) {
		return Sale.builder()
				.id(null)
				.code(saleDTO.getCodigo())
				.saleDate(LocalDate.parse(saleDTO.getData(), DateTimeFormatter.ofPattern("dd-MM-yyyy")))
				.customer(customer)
				.totalSaleValue(new BigDecimal(saleDTO.getValorTotal()))
				.build();
	}
}
