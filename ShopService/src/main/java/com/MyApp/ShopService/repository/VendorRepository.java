package com.MyApp.ShopService.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.MyApp.ShopService.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

	@Transactional
	@Modifying
	@Query(value="INSERT INTO vendor_categories VALUE(?1,?2)", nativeQuery = true)
	void saveInVendorcategory(int vid, int cid);

	@Query(value="SELECT * FROM vendor WHERE id = ?1", nativeQuery = true)
	List<Vendor> showVendorForUi(int vid);

}
