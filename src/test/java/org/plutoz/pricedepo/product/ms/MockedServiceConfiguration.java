package org.plutoz.pricedepo.product.ms;

import org.mockito.Mockito;
import org.plutoz.pricedepo.product.ms.service.BrandService;
import org.plutoz.pricedepo.product.ms.service.CategoryService;
import org.plutoz.pricedepo.product.ms.service.CompanyService;
import org.plutoz.pricedepo.product.ms.service.impl.BrandServiceImpl;
import org.plutoz.pricedepo.product.ms.service.impl.CategoryServiceImpl;
import org.plutoz.pricedepo.product.ms.service.impl.CompanyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("mockedServices")
@Configuration
public class MockedServiceConfiguration {

	@Bean
	@Primary
	public BrandService mockedBrandService() {
		return Mockito.mock( BrandServiceImpl.class );
	}
	
	@Bean
	@Primary
	public CategoryService mockedCategoryService() {
		return Mockito.mock( CategoryServiceImpl.class );
	}
	
	@Bean
	@Primary
	public CompanyService mockedCompanyService() {
		return Mockito.mock( CompanyServiceImpl.class );
	}
}
