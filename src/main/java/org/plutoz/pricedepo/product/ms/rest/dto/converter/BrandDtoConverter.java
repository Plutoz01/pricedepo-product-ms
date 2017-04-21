package org.plutoz.pricedepo.product.ms.rest.dto.converter;

import org.plutoz.pricedepo.common.rest.dto.converter.AbstractDtoConverter;
import org.plutoz.pricedepo.product.ms.domain.Brand;
import org.plutoz.pricedepo.product.ms.domain.Company;
import org.plutoz.pricedepo.product.ms.rest.dto.BrandDto;
import org.plutoz.pricedepo.product.ms.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandDtoConverter extends AbstractDtoConverter<BrandDto, Brand> {

	@Autowired
	private CompanyService companyService;
	
	@Override
	public BrandDto toDto(Brand model) {
		BrandDto brandDto = super.toDto( model );
		brandDto.setOwnerId( model.getOwner() != null ? model.getOwner().getId() : null );
		return brandDto;
	}

	@Override
	public Brand toModel(BrandDto dto) {
		Brand brand = super.toModel( dto );
		
		if(dto.getOwnerId() != null){
			Company company = companyService.findOne( dto.getOwnerId() );
			if(company == null){
				throw new IllegalArgumentException("Company not exists with id: " + dto.getOwnerId() );
			}
			brand.setOwner( company );
		}
		
		return brand;
	}
}
