package org.plutoz.pricedepo.product.ms.rest.controller;

import org.plutoz.pricedepo.product.ms.domain.Product;
import org.plutoz.pricedepo.product.ms.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController extends AbstractRestController<Long, Product> {

	public ProductController(ProductService productService) {
		super(productService);
	}
}
