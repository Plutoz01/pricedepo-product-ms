package org.plutoz.pricedepo.product.ms.repository;

import org.plutoz.pricedepo.product.ms.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	public Page<Category> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);
}
