package com.winestore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winestore.dao.CustomerRepository;
import com.winestore.domain.Customer;
import com.winestore.domain.Product;
import com.winestore.dto.CustomerDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Transactional
	public void save(CustomerDTO customerDTO) {
		String document = formatDocumentToOnlyNumbers(customerDTO.getCpf());
		Customer customer = findByDocument(document);

		if (null != customer) {
			if (customer.getId() == customerDTO.getId()) {
				customer.setName(customerDTO.getNome());
				repository.save(customer);
				log.info("Customer updated");

			} else {
				log.info(
						"Customer of id={}, document={} and name={}, wasn't update. This document belongs to customer with id={} ",
						customerDTO.getId(), customerDTO.getCpf(), customerDTO.getNome(), customer.getId());
			}

		} else {
			repository.save(Customer.builder().name(customerDTO.getNome()).document(document).build());
			log.info("Customer created");
		}
	}

	@Transactional
	private Customer saveAsAnonymous(String document) {
		return repository.save(new Customer(null, "NOT_REGISTERED", document));
	}

	private Customer findByDocument(String document) {
		return repository.findByDocument(document).orElse(null);
	}

	public Customer getCustomer(String document) {
		document = formatDocumentToOnlyNumbers(document);
		Customer customer = findByDocument(document);

		if (null == customer) {
			customer = saveAsAnonymous(document);
		}

		return customer;
	}

	private String formatDocumentToOnlyNumbers(String document) {
		String onlyNumbersDoc = document.replaceAll("[^0-9]", "");

		if (onlyNumbersDoc.length() > 11) {
			int endIndex = onlyNumbersDoc.length();
			int startIndex = endIndex - 11;
			onlyNumbersDoc = onlyNumbersDoc.substring(startIndex, endIndex);
			log.warn("Customer document with more than 11 digits. Document replaced from={} to={}", document,
					onlyNumbersDoc);
		} else if (onlyNumbersDoc.length() < 11) {
			log.warn("Customer document with less than 11 digits. Document used anyway!");			
		}

		return onlyNumbersDoc;
	}

	public Product findWineToFitCustomerTaste(Customer customer) {
		return null;
	}

}
