package com.winestore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "PRODUCT_TYPE")
	private String productType;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "VARIETY")
	private String variety;
	
	@Column(name = "CATEGORY")
	private String category;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "SAFRA")
	private String safra;
	
	@Column(name = "UNIT_SELLING_PRICE")
	private BigDecimal unitSellingPrice;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.product")
	final private Set<ItemSale> items = new HashSet<ItemSale>();
		
}
