package com.winestore.mapper;

import org.springframework.stereotype.Component;

import com.winestore.domain.Customer;
import com.winestore.dto.CustomerDTO;

@Component
public class CustomerMapper {

	public Customer map(CustomerDTO dto) {
		return new Customer(dto.getId(), dto.getNome(), dto.getCpf());
	}

}
