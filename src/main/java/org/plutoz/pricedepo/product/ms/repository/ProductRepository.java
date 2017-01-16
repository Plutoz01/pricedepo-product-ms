package org.plutoz.pricedepo.product.ms.repository;

import org.plutoz.pricedepo.product.ms.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
	
	public Page<Product> findByManufacturer(String manufacturer, Pageable pageable);
	
	public Page<Product> findByBrand(String brand, Pageable pageable);
}
