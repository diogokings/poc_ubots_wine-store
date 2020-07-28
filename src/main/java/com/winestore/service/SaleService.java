package com.winestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winestore.dao.SaleRepository;
import com.winestore.domain.Customer;
import com.winestore.domain.Sale;
import com.winestore.dto.Report;
import com.winestore.dto.SaleDTO;
import com.winestore.mapper.SaleMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Autowired
	private ItemSaleService itemSaleService;
	
	@Autowired
	private CustomerService customerService;
	
	@Transactional
	public void save(SaleDTO dto) {
		Sale entity = findByCode(dto.getCodigo());

		if (null != entity) {
			log.info("Sale code={} already exists! For now we are not updating existent sales!"
					, dto.getCodigo());
			return;
		}
		
		Sale sale = repository.save(SaleMapper.map(dto, customerService.getCustomer(dto.getCliente())));

		itemSaleService.save(sale, dto.getItens());
				
		log.info("Sale created!!!");
	}

	private Sale findByCode(String code) {
		Optional<Sale> entity = repository.findByCode(code);
		return entity.orElse(null);
	}
	
	public List<Customer> listCustomersOrderByBiggerBuy() {
//		if (null == customers || customers.isEmpty()) {
//			return new ArrayList<Customer>();
//		}
		List<Report> list= repository.findSum();
		return null;
	}

	public Customer findCustomerWithBiggerBuyInYear() {
		return null;
	}

	public List<Customer> listLoyalCustomers() {
		return null;
	}

}
