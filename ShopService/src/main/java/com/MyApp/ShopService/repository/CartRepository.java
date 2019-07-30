package com.MyApp.ShopService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.MyApp.ShopService.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
