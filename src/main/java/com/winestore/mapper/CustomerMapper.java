package com.winestore.mapper;

import com.winestore.domain.Customer;
import com.winestore.dto.CustomerDTO;

public class CustomerMapper {

	public static Customer map(CustomerDTO dto) {
		return new Customer(dto.getId(), dto.getNome(), dto.getCpf());
	}

}
