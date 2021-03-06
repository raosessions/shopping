package com.vsoft.shopping.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsoft.shopping.model.Order;
import com.vsoft.shopping.model.Product;
import com.vsoft.shopping.response.ServerResponse;
import com.vsoft.shopping.service.ProductService;

@RestController
@RequestMapping("/admin")
@CrossOrigin()
public class AdminController {
	@Autowired
	ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product p) {
		// Validate the product object
		
		// Call the service class to add the product
		Product prod = this.productService.addProduct(p);
		// 
		return new ResponseEntity<Product>(prod,HttpStatus.OK);
	}
	
	@PutMapping("/product")
	public ResponseEntity<ServerResponse> updateProducts() {
		ServerResponse response = new ServerResponse();
		return new ResponseEntity<ServerResponse>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/product")
	public ResponseEntity<ServerResponse> delProduct(@RequestParam(name = "") String productid){
		ServerResponse response = new ServerResponse();
		return new ResponseEntity<ServerResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> viewOrders() {
		return null ;
	}
	
	@PostMapping("/updateOrder")
	public ResponseEntity<ServerResponse> updateOrders() {
		ServerResponse response = new ServerResponse();
		return new ResponseEntity<ServerResponse>(response,HttpStatus.OK);		
	}
}
