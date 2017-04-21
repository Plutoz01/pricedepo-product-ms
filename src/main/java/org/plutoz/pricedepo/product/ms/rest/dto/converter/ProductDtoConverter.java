package org.plutoz.pricedepo.product.ms.rest.dto.converter;

import java.util.Set;
import java.util.stream.Collectors;

import org.plutoz.pricedepo.common.rest.dto.converter.AbstractDtoConverter;
import org.plutoz.pricedepo.product.ms.domain.Brand;
import org.plutoz.pricedepo.product.ms.domain.Category;
import org.plutoz.pricedepo.product.ms.domain.Company;
import org.plutoz.pricedepo.product.ms.domain.Measurement;
import org.plutoz.pricedepo.product.ms.domain.Product;
import org.plutoz.pricedepo.product.ms.rest.dto.ProductDto;
import org.plutoz.pricedepo.product.ms.service.BrandService;
import org.plutoz.pricedepo.product.ms.service.CategoryService;
import org.plutoz.pricedepo.product.ms.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoConverter extends AbstractDtoConverter<ProductDto, Product> {

	@Autowired
	private BrandService brandService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CompanyService companyService;
	
	@Override
	public ProductDto toDto(Product model) {
		ProductDto productDto = super.toDto( model );
		productDto.setBrandId( model.getBrand() != null ? model.getBrand().getId() : null );
		productDto.setManufacturerId( model.getManufacturer() != null ? model.getManufacturer().getId() : null );
		productDto.setCategories( 
			model.getCategories().stream()
				.map( Category::getName )
				.collect( Collectors.toSet() )
		);
		productDto.setMeasurement( model.getMeasurement().getAbbrevation() );
		return productDto;
	}

	@Override
	public Product toModel(ProductDto dto) {
		Product product = super.toModel( dto );
		
		if(dto.getBrandId() != null) {
			Brand brand = brandService.findOne( dto.getBrandId() );
			if(brand == null){
				throw new IllegalArgumentException("Brand not exists with id: " + dto.getBrandId() );
			}
			product.setBrand( brand );
		}
		
		if( !dto.getCategories().isEmpty() ){
			Set<Category> categories = dto.getCategories().stream()
					.map( this::findCategoryByName )
					.collect( Collectors.toSet() );
			product.setCategories(categories);
		}
		
		if(dto.getManufacturerId() != null){
			Company company = companyService.findOne( dto.getManufacturerId() );
			if(company == null){
				throw new IllegalArgumentException("Company not exists with id: " + dto.getManufacturerId() );
			}
			product.setManufacturer( company );
		}
		
		Measurement measurement = Measurement.get( dto.getMeasurement() );
		if( measurement == null ) {
			throw new IllegalArgumentException("Invalid measurement: " + dto.getMeasurement() );
		}
		product.setMeasurement( measurement );
		
		return product;
	}
	
	private Category findCategoryByName( String categoryName ){
		Category result = this.categoryService.findByNameIgnoreCase(categoryName);
		
		if( result == null ){
			throw new IllegalArgumentException("Category not exists with name: " + categoryName);
		}
		
		return result;
	}
}
