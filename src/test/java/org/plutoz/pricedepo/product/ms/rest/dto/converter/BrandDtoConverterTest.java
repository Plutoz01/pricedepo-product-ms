package org.plutoz.pricedepo.product.ms.rest.dto.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.plutoz.pricedepo.common.rest.dto.converter.DtoConverter;
import org.plutoz.pricedepo.product.ms.MockedServiceConfiguration;
import org.plutoz.pricedepo.product.ms.RestConfiguration;
import org.plutoz.pricedepo.product.ms.domain.Brand;
import org.plutoz.pricedepo.product.ms.rest.dto.BrandDto;
import org.plutoz.pricedepo.product.ms.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles( { "test", "mockedServices" } )
@RunWith( SpringRunner.class )
@ContextConfiguration( classes = { RestConfiguration.class, MockedServiceConfiguration.class, DtoConverterConfiguration.class } )
public class BrandDtoConverterTest {

	private static final BrandDto TEST_BRAND_DTO = new BrandDto();
	
	static {
		TEST_BRAND_DTO.setId( TestData.TEST_BRAND.getId() );
		TEST_BRAND_DTO.setLogoUrl( TestData.TEST_BRAND.getLogoUrl() );
		TEST_BRAND_DTO.setName( TestData.TEST_BRAND.getName() );
		TEST_BRAND_DTO.setOwnerId( TestData.TEST_BRAND.getOwner().getId() );
	}


	@Autowired
	private CompanyService companyService;
	@Autowired
	private DtoConverter<BrandDto, Brand> converter;

	@Before
	public void setUp() {
		when( companyService.findOne( ( Long ) notNull() ) ).thenReturn( TestData.TEST_BRAND.getOwner() );		
	}

	@Test
	public void dtoToModelTest() {
		final Brand converted = converter.toModel( TEST_BRAND_DTO );

		assertEquals( "ids are not equal", TEST_BRAND_DTO.getId(), converted.getId() );
		assertEquals( "logoUrls are not equal", TEST_BRAND_DTO.getLogoUrl(), converted.getLogoUrl() );
		assertEquals( "names are not equal", TEST_BRAND_DTO.getName(), converted.getName() );
		assertEquals( "owners are not equal", TestData.TEST_COMPANY, converted.getOwner() );
	}

	@Test
	public void modelToDtoTest() {
		final BrandDto converted = converter.toDto( TestData.TEST_BRAND );
		
		assertEquals( "ids are not equal", TestData.TEST_BRAND.getId(), converted.getId() );
		assertEquals( "logoUrls are not equal", TestData.TEST_BRAND.getLogoUrl(), converted.getLogoUrl() );
		assertEquals( "names are not equal", TestData.TEST_BRAND.getName(), converted.getName() );
		assertEquals( "owners are not equal", TestData.TEST_BRAND.getOwner().getId(), converted.getOwnerId() );
	}

}
