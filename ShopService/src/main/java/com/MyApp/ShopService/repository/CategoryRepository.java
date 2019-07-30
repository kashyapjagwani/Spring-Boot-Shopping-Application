package com.MyApp.ShopService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MyApp.ShopService.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
