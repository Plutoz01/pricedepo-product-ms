package org.plutoz.pricedepo.product.ms.rest.controller;

import java.io.Serializable;

import org.plutoz.pricedepo.common.domain.Identifiable;
import org.plutoz.pricedepo.common.rest.exception.ResourceNotFoundException;
import org.plutoz.pricedepo.common.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractRestController<ID extends Serializable, T extends Identifiable<ID>> {
	
	private final CrudService<ID, T> crudService;	
	
	public AbstractRestController(CrudService<ID, T> crudService){
		this.crudService = crudService;
	}
	
	@GetMapping
	public Page<T> getAll(Pageable pageable){
		return crudService.findAll(pageable);
	}	
	
	@GetMapping("/{id}")
	public T findById(@PathVariable("id") ID id){
		T result = crudService.findOne(id);
		
		if(result == null){
			throw new ResourceNotFoundException();
		}
		
		return result;
	}
	
	@PostMapping
	public T create(@RequestBody T entity){
		return crudService.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable ID id){
		crudService.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity){
		if(!crudService.exists(id)){
			return ResponseEntity.notFound().build();
		}
		
		entity.setId(id);
		T updated = crudService.save(entity);
		return ResponseEntity.ok(updated);
	}	
	
}
