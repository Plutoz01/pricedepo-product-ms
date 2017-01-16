package org.plutoz.pricedepo.product.ms.repository;

import org.plutoz.pricedepo.product.ms.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
	
	public Page<Product> findByManufacturer(@Param("manufacturer") String manufacturer, Pageable pageable);
	
	public Page<Product> findByBrand(@Param("brand") String brand, Pageable pageable);
}
