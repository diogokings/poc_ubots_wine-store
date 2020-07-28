package com.winestore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.winestore.domain.Sale;
import com.winestore.dto.Report;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	Optional<Sale> findByCode(String code);

	@Query(nativeQuery = true, value = ""
			+ "SELECT CUSTOMER_ID, SUM(TOTAL_SALE_VALUE) " 
			+ "  FROM SALE " 
			+ " GROUP BY CUSTOMER_ID " 
			+ "ORDER BY SUM(TOTAL_SALE_VALUE) DESC")
	List<Report> findSum();
}
