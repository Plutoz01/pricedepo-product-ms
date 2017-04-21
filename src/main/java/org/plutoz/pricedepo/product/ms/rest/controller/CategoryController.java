package org.plutoz.pricedepo.product.ms.rest.controller;

import org.plutoz.pricedepo.common.rest.controller.AbstractRestController;
import org.plutoz.pricedepo.product.ms.domain.Category;
import org.plutoz.pricedepo.product.ms.rest.dto.CategoryDto;
import org.plutoz.pricedepo.product.ms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractRestController<Long, Category, CategoryDto> {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/search/name/{name}")
	public Page<Category> findByName(@PathVariable("name") String name, Pageable pageable) {
		return categoryService.findByNameContainingIgnoreCase(name, pageable);
	}
}
