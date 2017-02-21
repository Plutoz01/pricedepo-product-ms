package org.plutoz.pricedepo.product.ms.service;

import org.plutoz.pricedepo.common.service.CrudService;
import org.plutoz.pricedepo.product.ms.domain.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService extends CrudService<Long, Company> {

	public Page<Company> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
