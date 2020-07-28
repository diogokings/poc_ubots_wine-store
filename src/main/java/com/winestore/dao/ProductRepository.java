package com.winestore.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.winestore.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true, value = ""
			+ "SELECT * "
			+ "  FROM PRODUCT P "
			+ " WHERE P.PRODUCT_TYPE = :productType "
			+ "	  AND P.DESCRIPTION = :description "
			+ "   AND P.VARIETY = :variety "
			+ "   AND P.CATEGORY = :category "
			+ "   AND P.COUNTRY = :country "
			+ "   AND P.SAFRA = :safra "
			+ "   AND rownum <= 1")
	Optional<Product> findByComparingFields(@Param("productType") String productType, 
			@Param("description") String description, @Param("variety") String variety, 
			@Param("category") String category, @Param("country") String country, 
			@Param("safra") String safra);
	
}
