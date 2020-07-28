package com.winestore.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winestore.dao.ProductRepository;
import com.winestore.domain.Product;
import com.winestore.dto.ProductDTO;
import com.winestore.mapper.ProductMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Transactional
	public Product save(String productType, ProductDTO productDTO) {
		Product product = findByComparingFields(productType, productDTO);

		if (null != product) {
			product.setUnitSellingPrice(new BigDecimal(productDTO.getPreco()));
			product = repository.save(product);
			log.info("Product updated");	

		} else {
			product = repository.save(ProductMapper.map(productDTO));
			log.info("Product created");	
		}

		return product;
	}

	@Transactional
	public void saveAll(List<Product> products) {
		repository.saveAll(products);
	}

	public Product findById(Long id) {
		Optional<Product> entity = repository.findById(id);
		return entity.orElse(null);
	}

	private Product findByComparingFields(String productType, ProductDTO dto) {
		Optional<Product> entity = repository.findByComparingFields(productType, dto.getProduto(), dto.getVariedade(),
				dto.getCategoria(), dto.getPais(), dto.getSafra());
		return entity.orElse(null);
	}
	
	public List<Product> getProducts(String productType, ProductDTO[] productsDTO) {
		List<Product> products = new ArrayList<Product>();

		for (ProductDTO dto : productsDTO) {
			products.add(save("WINE", dto));
		}
		
		return products;
	}

}
