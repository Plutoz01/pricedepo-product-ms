package org.plutoz.pricedepo.product.ms.service;

import static java.util.Objects.requireNonNull;

import org.plutoz.pricedepo.common.service.AbstractCrudService;
import org.plutoz.pricedepo.product.ms.domain.Product;
import org.plutoz.pricedepo.product.ms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends AbstractCrudService<Product, Long> implements ProductService {

	private ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository){
		super(productRepository);
		this.productRepository = productRepository;
	}
	
	@Override
	public Page<Product> findByManufacturer(String manufacturer, Pageable pageable) {
		requireNonNull(manufacturer, "manufacturer can not be null");

		return productRepository.findByManufacturer(manufacturer, pageable);
	}

	@Override
	public Page<Product> findByBrand(String brand, Pageable pageable) {
		requireNonNull(brand, "brand can not be null");
		
		return productRepository.findByBrand(brand, pageable);
	}
}
