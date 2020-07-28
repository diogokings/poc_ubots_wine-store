package com.winestore.mapper;

import java.math.BigDecimal;

import com.winestore.domain.Product;
import com.winestore.dto.ProductDTO;

public class ProductMapper {

	public static Product map(ProductDTO productDTO) {
		return Product.builder()
				.id(null)
				.productType("WINE")
				.description(productDTO.getProduto())
				.variety(productDTO.getVariedade())
				.category(productDTO.getCategoria())
				.country(productDTO.getPais())
				.safra(productDTO.getSafra())
				.unitSellingPrice(new BigDecimal(productDTO.getPreco()))
				.build();
	}

}
