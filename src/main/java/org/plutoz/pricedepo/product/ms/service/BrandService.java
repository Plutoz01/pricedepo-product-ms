package org.plutoz.pricedepo.product.ms.service;

import org.plutoz.pricedepo.common.service.CrudService;
import org.plutoz.pricedepo.product.ms.domain.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService extends CrudService<Long, Brand> {
	
	public Page<Brand> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
