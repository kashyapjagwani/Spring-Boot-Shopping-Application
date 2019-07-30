package com.MyApp.ShopService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MyApp.ShopService.model.Category;
import com.MyApp.ShopService.repository.CategoryRepository;
import com.MyApp.ShopService.repository.ProductRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/getall")
	public List<Category> getCategory(){
		List<Category> list_without_count = categoryRepository.findAll();
		List<Category> list_with_count = new ArrayList<>();
		for(Category c:list_without_count) {
			int count = productRepository.findByCategoryId(c.getId()).size();
			c.setCount(count);
			list_with_count.add(c);
		}
		return list_with_count;
	}
	
	@PostMapping("/post/{name}")
	public Category postCategory(@PathVariable String name) {
		Category category = new Category();
		category.setName(name);
		return categoryRepository.save(category);
	}
	
}
