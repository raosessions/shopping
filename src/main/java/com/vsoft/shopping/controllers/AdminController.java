package com.vsoft.shopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsoft.shopping.model.Product;
import com.vsoft.shopping.service.ProductService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	ProductService productService;
	
	@PostMapping("/addproduct")
	public ResponseEntity<String> addProduct(@RequestBody Product p) {
		// Validate the product object
		
		
		// Call the service class to add the product
		Product prod = this.productService.addProduct(p);
		// 
		return new ResponseEntity<String>("" + prod.getProductId(),HttpStatus.OK);
	}
}
