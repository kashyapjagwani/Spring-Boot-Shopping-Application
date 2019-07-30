package com.MyApp.ShopService.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.MyApp.ShopService.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Transactional
	@Modifying
	@Query(value="SELECT * FROM product WHERE category_id = ?1 AND vendor_id = ?2", nativeQuery=true)
	public List<Product> getProductCategoryVendor(int cid, int vid);

	@Query(value="SELECT * FROM product WHERE price <= ?1", nativeQuery = true)
	public List<Product> getProductParam(int price);

	@Query(value="SELECT * FROM product WHERE category_id = ?1", nativeQuery = true)
	public List<Product> findByCategoryId(int id);

	@Query(value="SELECT * FROM product WHERE product_of_day = 'YES'", nativeQuery = true)
	public List<Product> getProductOfDay();

	@Query(value="SELECT * FROM product WHERE id = ?1", nativeQuery = true)
	public List<Product> getOnlyOneForUi(int pid);

}
