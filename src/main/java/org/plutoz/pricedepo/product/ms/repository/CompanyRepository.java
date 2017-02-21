package org.plutoz.pricedepo.product.ms.repository;

import org.plutoz.pricedepo.product.ms.domain.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
	
	public Page<Company> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
