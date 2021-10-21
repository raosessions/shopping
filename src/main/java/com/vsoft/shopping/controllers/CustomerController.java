package com.vsoft.shopping.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsoft.shopping.model.CartItem;
import com.vsoft.shopping.model.Order;
import com.vsoft.shopping.model.Product;
import com.vsoft.shopping.response.ServerResponse;
import com.vsoft.shopping.service.ProductService;

@RestController
@RequestMapping("/user")
@CrossOrigin()
public class CustomerController {
	@Autowired
	ProductService productService;

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
		return new ResponseEntity<Product>(this.productService.getProduct(id), HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		return new ResponseEntity<List<Product>>(this.productService.getProducts(), HttpStatus.OK);
	}
	

	
	@PostMapping("/cart")
	public ResponseEntity<ServerResponse> addItemToCart() {
		ServerResponse response = new ServerResponse();
		return new ResponseEntity<ServerResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/cart")
	public ResponseEntity<List<CartItem>> getCart() {
		return null;
	}
	
	@PutMapping("/cart")
	public ResponseEntity<ServerResponse> updateCart() {
		ServerResponse response = new ServerResponse();
		return new ResponseEntity<ServerResponse>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/cart")
	public ResponseEntity<ServerResponse> deleteCart() {
		ServerResponse response = new ServerResponse();
		return new ResponseEntity<ServerResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping("/order")
	public ResponseEntity<Order> placeOrder() {
		return null ;
	}
}
