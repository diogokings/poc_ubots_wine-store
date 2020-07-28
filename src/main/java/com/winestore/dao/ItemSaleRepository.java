package com.winestore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winestore.domain.ItemSale;

@Repository
public interface ItemSaleRepository extends JpaRepository<ItemSale, Long> {

}
