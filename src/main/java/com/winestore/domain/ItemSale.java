package com.winestore.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ITEM_SALE")
public class ItemSale implements Serializable {

	private static final long serialVersionUID = 1L;

	@Include
	@JsonIgnore
	@EmbeddedId
	private ItemSalePK id;

	@Column(name = "UNIT_SOLD_PRICE")
	private BigDecimal unitSoldPrice;

	@Builder
	public ItemSale(Sale sale, Product product, BigDecimal unitSoldPrice) {
		this.id = new ItemSalePK(sale, product);
		this.unitSoldPrice = unitSoldPrice;
	}

	
}