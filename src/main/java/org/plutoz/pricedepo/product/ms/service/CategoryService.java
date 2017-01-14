package org.plutoz.pricedepo.product.ms.service;

import org.plutoz.pricedepo.common.service.CrudService;
import org.plutoz.pricedepo.product.ms.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService extends CrudService<Category, Long> {
	
	public Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
