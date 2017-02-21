package org.plutoz.pricedepo.product.ms.service.impl;

import static java.util.Objects.requireNonNull;

import org.plutoz.pricedepo.common.service.AbstractCrudService;
import org.plutoz.pricedepo.product.ms.domain.Product;
import org.plutoz.pricedepo.product.ms.repository.ProductRepository;
import org.plutoz.pricedepo.product.ms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends AbstractCrudService<Long, Product> implements ProductService {

	private ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository){
		super(productRepository);
		this.productRepository = productRepository;
	}
}
