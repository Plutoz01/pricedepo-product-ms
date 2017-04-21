package org.plutoz.pricedepo.product.ms.rest.dto.converter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

import org.plutoz.pricedepo.product.ms.domain.Brand;
import org.plutoz.pricedepo.product.ms.domain.Category;
import org.plutoz.pricedepo.product.ms.domain.Company;
import org.plutoz.pricedepo.product.ms.domain.Measurement;
import org.plutoz.pricedepo.product.ms.domain.Product;

final class TestData {
	
	static final Company TEST_COMPANY = new Company( 100L, "test company", new HashSet<>(), new HashSet<>() );
	static final Brand TEST_BRAND = new Brand( 200L, "test brand", "http://test.url/image.png", TEST_COMPANY, new HashSet<Product>() );
	static final Category TEST_CATEGORY = new Category( 300L, "test category" );
	static final Product TEST_PRODUCT = new Product( 400L, TEST_BRAND, TEST_COMPANY, "test product",
			new HashSet<Category>( Arrays.asList( TEST_CATEGORY ) ), new BigDecimal( 175 ), Measurement.WEIGHT_MILLIGRAMM );
	
	static {
		TEST_COMPANY.getBrands().add( TEST_BRAND );
		TEST_COMPANY.getProducts().add( TEST_PRODUCT );
		TEST_BRAND.getProducts().add( TEST_PRODUCT );
	}
	
	private TestData() {}
}
