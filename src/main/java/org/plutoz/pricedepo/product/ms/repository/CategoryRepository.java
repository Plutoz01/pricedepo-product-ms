package org.plutoz.pricedepo.product.ms.repository;

import org.plutoz.pricedepo.product.ms.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
	
	public Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);
	
	public Category findByNameIgnoreCase(String name);
}
