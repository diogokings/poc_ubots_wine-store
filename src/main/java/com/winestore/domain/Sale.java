package com.winestore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "SALE")
public class Sale implements Serializable {

	private static final long serialVersionUID = 1L;

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CODE", unique=true)
	private String code;
	
	@Column(name = "SALE_DATE", columnDefinition = "DATE")
	private LocalDate saleDate;
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID", foreignKey = @ForeignKey(name = "FK_SALE_CUSTOMER"))
	private Customer customer;
	
	@Column(name = "TOTAL_SALE_VALUE")
	private BigDecimal totalSaleValue;

	@OneToMany(mappedBy = "id.sale")
	final private Set<ItemSale> items = new HashSet<ItemSale>();

}
