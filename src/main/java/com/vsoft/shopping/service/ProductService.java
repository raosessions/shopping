package com.vsoft.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsoft.shopping.model.Product;
import com.vsoft.shopping.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public Product addProduct(Product product) {
		return this.productRepository.save(product);
	}
}
