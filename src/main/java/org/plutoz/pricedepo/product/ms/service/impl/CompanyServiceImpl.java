package org.plutoz.pricedepo.product.ms.service.impl;

import static java.util.Objects.requireNonNull;

import org.plutoz.pricedepo.common.service.AbstractCrudService;
import org.plutoz.pricedepo.product.ms.domain.Company;
import org.plutoz.pricedepo.product.ms.repository.CompanyRepository;
import org.plutoz.pricedepo.product.ms.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends AbstractCrudService<Long, Company> implements CompanyService {

	private final CompanyRepository companyRepository;
	
	@Autowired
	public CompanyServiceImpl(CompanyRepository companyRepository){
		super(companyRepository);
		this.companyRepository = companyRepository;
	}

	@Override
	public Page<Company> findByNameContainingIgnoreCase(String name, Pageable pageable) {
		requireNonNull(name, "Company name can not be null");
		
		return companyRepository.findByNameContainingIgnoreCase(name, pageable); 
	}
}
