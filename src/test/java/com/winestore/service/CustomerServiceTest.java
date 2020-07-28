package com.winestore.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.winestore.dao.CustomerRepository;
import com.winestore.domain.Customer;
import com.winestore.domain.Sale;
import com.winestore.dto.CustomerDTO;
import com.winestore.dto.ProductDTO;
import com.winestore.dto.SaleDTO;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService service;
	
	@Mock
	private CustomerRepository repository;

	@Test
	public void save_givenCustomer_whenNullIdAndFilledNameAndDocument_thenInsert() {
		when(repository.findById(any())).thenReturn(Optional.ofNullable(null));
		when(repository.save(any())).thenReturn(mockCustomer(1L));
		service.save(mockCustomerDTO(null));
		
		ArgumentCaptor<Customer> argument = ArgumentCaptor.forClass(Customer.class);
		verify(repository).save(argument.capture());
		
		assertEquals("Diogo Reis", argument.getValue().getName());
		assertEquals("01234567890", argument.getValue().getDocument());
	}

	@Test
	public void save_givenCustomer_whenHasIdNameAndDocument_thenUpdate() {		
		when(repository.findById(any())).thenReturn(Optional.of(mockCustomer(1L)));
		when(repository.save(any())).thenReturn(mockCustomer(1L));
		service.save(mockCustomerDTO(1L));
		
		ArgumentCaptor<Customer> argument = ArgumentCaptor.forClass(Customer.class);
		verify(repository).save(argument.capture());
		
		assertEquals("Diogo Reis", argument.getValue().getName());
		assertEquals("01234567890", argument.getValue().getDocument());
	}
	/*
	@Test
	public void listCustomersOrderByBiggerBuy_giveCustomerList_whenNull_thenReturnAnEmptyList() {
		List<Customer> nullCustomers = service.listCustomersOrderByBiggerBuy(null);
		assertEquals(new ArrayList<Customer>(), nullCustomers);
	}

	@Test
	public void listCustomersOrderByBiggerBuy_giveCustomerList_whenEmpty_thenReturnAnEmptyList() {
		List<Customer> emptyCustomers = service.listCustomersOrderByBiggerBuy(new ArrayList<Customer>());
		assertEquals(new ArrayList<Customer>(), emptyCustomers);
	}

	@Test
	public void listCustomersOrderByBiggerBuy_giveCustomerList_whenHasOnlyOneCustomer_thenReturnAList() {
		List<Customer> expectedCustomers = new ArrayList<Customer>();

//		List<Customer> emptyCustomers = service.listCustomersOrderByBiggerBuy();
//		assertEquals(new ArrayList<Customer>(), emptyCustomers);
	}

	 */
	private Customer mockCustomer(Long id) {
		return new Customer(id, "Diogo Reis", "01234567890");
	}

	private CustomerDTO mockCustomerDTO(Long id) {
		return new CustomerDTO(id, "Diogo Reis", "01234567890");
	}
	
	private List<Customer> mockCustomerList() {
		ProductDTO prod1 = new ProductDTO("Casa Silva Reserva", "Cabernet Sauvignon", "Chile",  "Tinto", "2014", 79D);
		ProductDTO prod2 = new ProductDTO("Punto Final Etiqueta Negra", "Malbec", "Argentina",  "Tinto", "2012", 59.9D);
		ProductDTO prod3 = new ProductDTO("Punto Final", "Malbec", "Argentina",  "Tinto", "2016", 59.9D);
		ProductDTO prod4 = new ProductDTO("Casa Valduga", "Merlot", "Brasil",  "Tinto", "2014", 55D);
		
		ProductDTO[] prods = {prod1, prod2, prod3, prod4};
		
		List<Sale> sales = new ArrayList<Sale>();
		SaleDTO sale1 = new SaleDTO("3fde36a6-c9a1-4d27-9f0f-7c12ab0d1cdd", "19-02-2016", "000.000.000.01", prods, 56d);
		
		
	//	List<Customer> expectedCustomers = new ArrayList<Customer>();
		
		return null;
		
	}
}
