package org.plutoz.pricedepo.product.ms.service;

import org.plutoz.pricedepo.common.service.CrudService;
import org.plutoz.pricedepo.product.ms.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService extends CrudService<Product, Long> {
	
	public Page<Product> findByManufacturer(String manufacturer, Pageable pageable);
	
	public Page<Product> findByBrand(String brand, Pageable pageable);
}
