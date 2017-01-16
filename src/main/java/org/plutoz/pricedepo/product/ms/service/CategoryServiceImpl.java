package org.plutoz.pricedepo.product.ms.service;

import static java.util.Objects.requireNonNull;

import org.plutoz.pricedepo.common.service.AbstractCrudService;
import org.plutoz.pricedepo.product.ms.domain.Category;
import org.plutoz.pricedepo.product.ms.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends AbstractCrudService<Long, Category> implements CategoryService {

	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository){
		super(categoryRepository);
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable) {
		requireNonNull(name, "Category name can not be null");
		
		return categoryRepository.findByNameContainingIgnoreCase(name, pageable);
	}
}
