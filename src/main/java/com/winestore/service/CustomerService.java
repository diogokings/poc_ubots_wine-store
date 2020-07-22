package com.winestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winestore.dao.CustomerRepository;
import com.winestore.domain.Customer;
import com.winestore.domain.Product;
import com.winestore.dto.CustomerDTO;
import com.winestore.mapper.CustomerMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	@Transactional
	public void save(CustomerDTO dto) {
		Customer entity = findById(dto.getId());

		if (null != entity && !entity.getDocument().equalsIgnoreCase(dto.getCpf())) {
			log.info("Customer of document={} and name={}, wasn't update because this document belongs to another customer, with id={} "
					, dto.getCpf(), dto.getNome(), entity.getId());
			return;
		}

		repository.save(CustomerMapper.map(dto));
		log.info("Customer ".concat(null == entity ? "created" : "updated"));
	}

	public Customer findById(Long id) {
		Optional<Customer> entity = repository.findById(id);
		return entity.orElse(null);
	}

	public List<Customer> listCustomersOrderByBiggerBuy(List<Customer> customers) {
		if(null == customers || customers.isEmpty()) {
			return new ArrayList<Customer>();
		}
		return null;
	}
	
	public Customer findCustomerWithBiggerBuyInYear() {
		return null;
	}

	public List<Customer> listLoyalCustomers() {
		return null;
	}

	public Product findWineToFitCustomerTaste(Customer customer) {
		return null;
	}
}
