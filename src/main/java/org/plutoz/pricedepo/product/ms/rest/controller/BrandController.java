package org.plutoz.pricedepo.product.ms.rest.controller;

import org.plutoz.pricedepo.product.ms.domain.Brand;
import org.plutoz.pricedepo.product.ms.domain.Company;
import org.plutoz.pricedepo.product.ms.rest.dto.BrandDTO;
import org.plutoz.pricedepo.product.ms.service.BrandService;
import org.plutoz.pricedepo.product.ms.service.CompanyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brands")
public class BrandController extends AbstractRestController<Long, Brand, BrandDTO> {
	
	private final CompanyService companyService;

	public BrandController(BrandService brandService, CompanyService companyService) {
		super(brandService);
		this.companyService = companyService;
	}

	@Override
	protected Brand convertToEntity(BrandDTO dto) {		
		Brand result = super.convertToEntity(dto);
		if(dto.getOwnerId() != null){
			Company owner = companyService.findOne(dto.getOwnerId());
			if(owner == null){
				throw new IllegalArgumentException("Company not exists with id: " + dto.getOwnerId());
			}
			result.setOwner(owner);
		}
		
		return result;
	}
}
