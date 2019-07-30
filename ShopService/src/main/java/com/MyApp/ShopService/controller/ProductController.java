package com.MyApp.ShopService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MyApp.ShopService.model.Category;
import com.MyApp.ShopService.model.Product;
import com.MyApp.ShopService.model.Vendor;
import com.MyApp.ShopService.repository.CategoryRepository;
import com.MyApp.ShopService.repository.ProductRepository;
import com.MyApp.ShopService.repository.VendorRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private List<Integer> myCartItems = new ArrayList<Integer>();
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private VendorRepository vendorRepository;

	@GetMapping("/getall")
	public List<Product> getProduct(){
		return productRepository.findAll();
	}
	
	@GetMapping("/get/{cid}/{vid}")
	public List<Product> getSpecific(@PathVariable("cid") int cid, @PathVariable("vid") int vid){
		return productRepository.getProductCategoryVendor(cid,vid);
	}
	
	@GetMapping("/getone/{pid}")
	public List<Product> getOne(@PathVariable("pid") int pid){
		return productRepository.getOnlyOneForUi(pid);
	}
	
	@GetMapping("/get/{price}")
	public List<Product> getProductParam(@PathVariable int price){
		return productRepository.getProductParam(price);
	}
	
	@GetMapping("/get/categoryproduct/{cid}")
	public List<Product> getCategoryProduct(@PathVariable int cid){
		return productRepository.findByCategoryId(cid);
	}
	
	@GetMapping("/get/prodofday")
	public List<Product> getProductOfDay(){
		return productRepository.getProductOfDay();
	}
	
	@PostMapping("/addtocart/{pid}")
	public List<Product> addToCart(@PathVariable int pid){
		myCartItems.add(pid);
		return productRepository.findAllById(myCartItems);
	}
	
	@PostMapping("/removefromcart/{pid}")
	public List<Product> removeFromCart(@PathVariable int pid){
		int i = myCartItems.indexOf(pid);
		myCartItems.remove(i);
		return productRepository.findAllById(myCartItems);
	}
	
	@PostMapping("/post/{cid}/{vid}")
	public Product postCategory(@RequestBody Product product, @PathVariable("cid") int cid, 
			@PathVariable("vid") int vid) {
		
		Category category = categoryRepository.getOne(cid);
		if(category == null)
			throw new RuntimeException();
		Vendor vendor = vendorRepository.getOne(vid);
		if(vendor == null)
			throw new RuntimeException();
		product.setCategory(category);
		product.setVendor(vendor);
		return productRepository.save(product);
		
	}
}
