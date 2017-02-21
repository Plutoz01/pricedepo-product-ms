package org.plutoz.pricedepo.product.ms.service.impl;

import static java.util.Objects.requireNonNull;

import org.plutoz.pricedepo.common.service.AbstractCrudService;
import org.plutoz.pricedepo.product.ms.domain.Brand;
import org.plutoz.pricedepo.product.ms.repository.BrandRepository;
import org.plutoz.pricedepo.product.ms.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends AbstractCrudService<Long, Brand> implements BrandService {

	private final BrandRepository brandRepository;
	
	@Autowired
	public BrandServiceImpl(BrandRepository brandRepository){
		super(brandRepository);
		this.brandRepository = brandRepository;
	}

	@Override
	public Page<Brand> findByNameContainingIgnoreCase(String name, Pageable pageable) {
		requireNonNull(name, "Brand name can not be null");
		
		return brandRepository.findByNameContainingIgnoreCase(name, pageable); 
	}
}
