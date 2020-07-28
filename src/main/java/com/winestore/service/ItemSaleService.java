package com.winestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winestore.dao.ItemSaleRepository;
import com.winestore.domain.ItemSale;
import com.winestore.domain.Product;
import com.winestore.domain.Sale;
import com.winestore.dto.ProductDTO;

@Service
public class ItemSaleService {

	@Autowired
	private ItemSaleRepository repository;

	@Autowired
	private ProductService productService;

	public void save(Sale sale, ProductDTO[] items) {
		List<Product> prods = productService.getProducts("WINE", items);
		for (Product product : prods) {
			repository.save(ItemSale.builder()
					.sale(sale).product(product).unitSoldPrice(product.getUnitSellingPrice())
					.build());
		}
	}

}
