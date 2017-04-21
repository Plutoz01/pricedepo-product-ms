package org.plutoz.pricedepo.product.ms.rest.dto.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.plutoz.pricedepo.common.rest.dto.converter.DtoConverter;
import org.plutoz.pricedepo.product.ms.MockedServiceConfiguration;
import org.plutoz.pricedepo.product.ms.RestConfiguration;
import org.plutoz.pricedepo.product.ms.domain.Product;
import org.plutoz.pricedepo.product.ms.rest.dto.ProductDto;
import org.plutoz.pricedepo.product.ms.service.BrandService;
import org.plutoz.pricedepo.product.ms.service.CategoryService;
import org.plutoz.pricedepo.product.ms.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles( { "test", "mockedServices" } )
@RunWith( SpringRunner.class )
@ContextConfiguration( classes = { RestConfiguration.class, MockedServiceConfiguration.class, DtoConverterConfiguration.class } )
public class ProductDtoConverterTest {
	
private static final ProductDto TEST_PRODUCT_DTO = new ProductDto();
	
	static {
		TEST_PRODUCT_DTO.setId( TestData.TEST_PRODUCT.getId() );
		TEST_PRODUCT_DTO.setBrandId( TestData.TEST_PRODUCT.getBrand().getId() );
		TEST_PRODUCT_DTO.setFullName( TestData.TEST_PRODUCT.getFullName() );
		TEST_PRODUCT_DTO.setManufacturerId( TestData.TEST_PRODUCT.getManufacturer().getId() );
		TEST_PRODUCT_DTO.setMeasurement( TestData.TEST_PRODUCT.getMeasurement().getAbbrevation() );
		TEST_PRODUCT_DTO.setUnit( TestData.TEST_PRODUCT.getUnit() );
		TEST_PRODUCT_DTO.setCategories(
			TestData.TEST_PRODUCT.getCategories().stream()
				.map( category -> category.getName() )
				.collect( Collectors.toSet() )
		);
	}

	@Autowired
	private BrandService brandService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DtoConverter<ProductDto, Product> converter;

	@Before
	public void setUp() {
		when( brandService.findOne( ( Long ) notNull() ) ).thenReturn( TestData.TEST_PRODUCT.getBrand() );
		when( categoryService.findByNameIgnoreCase( ( String ) notNull() ) ).thenReturn( TestData.TEST_CATEGORY );
		when( companyService.findOne( ( Long ) notNull() ) ).thenReturn( TestData.TEST_PRODUCT.getManufacturer() );
	}

	@Test
	public void dtoToModelTest() {
		final Product converted = converter.toModel( TEST_PRODUCT_DTO );

		assertEquals( "ids are not equal", TestData.TEST_PRODUCT.getId(), converted.getId() );
		assertEquals( "brands are not equal", TestData.TEST_PRODUCT.getBrand(), converted.getBrand() );
		assertEquals( "categories are not equal", TestData.TEST_PRODUCT.getCategories(), converted.getCategories() );
		assertEquals( "fullNames are not equal", TestData.TEST_PRODUCT.getFullName(), converted.getFullName() );
		assertEquals( "manufacturers are not equal", TestData.TEST_PRODUCT.getManufacturer(), converted.getManufacturer() );
		assertEquals( "measurements are not equal", TestData.TEST_PRODUCT.getMeasurement(), converted.getMeasurement() );
		assertEquals( "units are not equal", TestData.TEST_PRODUCT.getUnit(), converted.getUnit() );
	}

	@Test
	public void modelToDtoTest() {
		final ProductDto converted = converter.toDto( TestData.TEST_PRODUCT );
		
		assertEquals( "ids are not equal", TEST_PRODUCT_DTO.getId(), converted.getId() );
		assertEquals( "brandIds are not equal", TEST_PRODUCT_DTO.getBrandId(), converted.getBrandId() );
		assertEquals( "categories are not equal", TEST_PRODUCT_DTO.getCategories(), converted.getCategories() );
		assertEquals( "fullNames are not equal", TEST_PRODUCT_DTO.getFullName(), converted.getFullName() );
		assertEquals( "manufacturers are not equal", TEST_PRODUCT_DTO.getManufacturerId(), converted.getManufacturerId() );
		assertEquals( "measurements are not equal", TEST_PRODUCT_DTO.getMeasurement(), converted.getMeasurement() );
		assertEquals( "units are not equal", TEST_PRODUCT_DTO.getUnit(), converted.getUnit() );
	}

}
