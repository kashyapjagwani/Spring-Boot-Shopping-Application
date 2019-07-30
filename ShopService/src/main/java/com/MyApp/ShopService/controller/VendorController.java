package com.MyApp.ShopService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MyApp.ShopService.model.Category;
import com.MyApp.ShopService.model.Vendor;
import com.MyApp.ShopService.repository.CategoryRepository;
import com.MyApp.ShopService.repository.VendorRepository;

@RestController
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/getall")
	public List<Vendor> getAll() {
		return vendorRepository.findAll();
	}
	
	@GetMapping("/show/vendor/{vid}")
	public List<Vendor> showVendorForUi(@PathVariable int vid){
		return vendorRepository.showVendorForUi(vid);
	}

	@PostMapping("/post/{vname}")
	public Vendor postVendor(@PathVariable String vname) {
		Vendor vendor = new Vendor();
		vendor.setName(vname);
		return vendorRepository.save(vendor);
	}

	@PostMapping("/{vid}/{cid}")
	public void postVendorCategory(@PathVariable("vid") int vid, @PathVariable("cid") int cid) {
		Vendor vendor = vendorRepository.getOne(vid);
		if (vendor == null)
			return;
		Category category = categoryRepository.getOne(cid);
		if (category == null)
			return;
		vendorRepository.saveInVendorcategory(vid, cid);

	}
	
	

}
