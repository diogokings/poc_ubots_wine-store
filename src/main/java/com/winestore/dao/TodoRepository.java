package com.winestore.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winestore.domain.Customer;

@Repository
public interface TodoRepository extends JpaRepository<Customer, Long>{

	Optional<Customer> findByDocument(String document);

}
