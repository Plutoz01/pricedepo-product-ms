package org.plutoz.pricedepo.product.ms.rest.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.plutoz.pricedepo.product.ms.domain.Brand;
import org.plutoz.pricedepo.product.ms.domain.Category;
import org.plutoz.pricedepo.product.ms.domain.Company;
import org.plutoz.pricedepo.product.ms.domain.Measurement;
import org.plutoz.pricedepo.product.ms.domain.Product;
import org.plutoz.pricedepo.product.ms.rest.dto.ProductDTO;
import org.plutoz.pricedepo.product.ms.service.BrandService;
import org.plutoz.pricedepo.product.ms.service.CategoryService;
import org.plutoz.pricedepo.product.ms.service.CompanyService;
import org.plutoz.pricedepo.product.ms.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController extends AbstractRestController<Long, Product, ProductDTO> {

	private final BrandService brandService;
	private final CategoryService categoryService;
	private final CompanyService companyService;
	
	public ProductController(
			ProductService productService,
			BrandService brandService,
			CategoryService categoryService,
			CompanyService companyService
	) {
		super(productService);
		this.brandService = brandService;
		this.categoryService = categoryService;
		this.companyService = companyService;
	}

	@Override
	protected Product convertToEntity(ProductDTO dto) {
		Product product = super.convertToEntity(dto);
		
		if( Measurement.valueOf( dto.getMeasurement() ) == null ){
			throw new IllegalArgumentException("Invalid measurement: " + dto.getMeasurement());
		}
		
		if(dto.getBrandId() != null){
			Brand brand = brandService.findOne(dto.getBrandId());
			if(brand == null){
				throw new IllegalArgumentException("Brand not exists with id: " + dto.getBrandId());
			}
			product.setBrand(brand);
		}
		
		if(dto.getManufacturerId() != null){
			Company company = companyService.findOne(dto.getManufacturerId());
			if(company == null){
				throw new IllegalArgumentException("Manufacturer not exists with id: " + dto.getManufacturerId());
			}
			product.setManufacturer(company);
		}
		
		if( !dto.getCategories().isEmpty() ){
			Set<Category> categories = dto.getCategories().stream()
					.map( this::findCategoryByName )
					.collect( Collectors.toSet() );
			product.setCategories(categories);
		}
		
		
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
