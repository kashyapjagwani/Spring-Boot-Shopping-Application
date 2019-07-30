package com.MyApp.ShopService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MyApp.ShopService.model.Cart;
import com.MyApp.ShopService.model.Product;
import com.MyApp.ShopService.repository.CartRepository;
import com.MyApp.ShopService.repository.ProductRepository;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/getall")
	public List<Cart> getAll(){
		return cartRepository.findAll();
	}
	
	@PostMapping("/addtocart/{pid}")
	public List<Cart> addToCart(@PathVariable int pid){
		for(Cart c:cartRepository.findAll()) {
			if(c.getProduct().getId()==pid)
				return cartRepository.findAll();
			
		}
		
		Product product = productRepository.getOne(pid);
		Cart cart = new Cart();
		cart.setProduct(product);
		cartRepository.save(cart);
		return cartRepository.findAll();
	}
	
	@PostMapping("/deletefromcart/{pid}")
	public List<Cart> deleteFromCart(@PathVariable int pid){
		for(Cart c:cartRepository.findAll()) {
			if(c.getProduct().getId() == pid) {
				cartRepository.delete(c);
			}
		}
		return cartRepository.findAll();
		
	}
}
