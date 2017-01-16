package org.plutoz.pricedepo.product.ms.rest.controller;

import org.plutoz.pricedepo.common.rest.exception.ResourceNotFoundException;
import org.plutoz.pricedepo.product.ms.domain.Category;
import org.plutoz.pricedepo.product.ms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	private final CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService){
		this.categoryService = categoryService;
	}
	
	@GetMapping
	public Iterable<Category> getAll(Pageable pageable){
		return categoryService.findAll(pageable);
	}
	
	@GetMapping("/{id}")
	public Category findById(@PathVariable("id") Long id){
		Category result = categoryService.findOne(id);
		
		if(result == null){
			throw new ResourceNotFoundException();
		}
		
		return result;
	}
	
	@GetMapping("/search/name/{name}")
	public Page<Category> findByName(@PathVariable("name") String name, Pageable pageable){
		return categoryService.findByNameContainingIgnoreCase(name, pageable);
	}
	
	@PostMapping
	public Category create(@RequestBody Category category){
		return categoryService.save(category);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id){
		categoryService.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category){
		if(!categoryService.exists(id)){
			return ResponseEntity.notFound().build();
		}
		
		category.setId(id);
		Category updated = categoryService.save(category);
		return ResponseEntity.ok(updated);
	}	
}
