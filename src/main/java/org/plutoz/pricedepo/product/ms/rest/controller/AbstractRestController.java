package org.plutoz.pricedepo.product.ms.rest.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.plutoz.pricedepo.common.domain.Identifiable;
import org.plutoz.pricedepo.common.rest.exception.ResourceNotFoundException;
import org.plutoz.pricedepo.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//TODO: move to common-lib after finalization
public abstract class AbstractRestController<ID extends Serializable, E extends Identifiable<ID>, DTO extends Identifiable<ID>> {
	
	private final CrudService<ID, E> crudService;
	
	private final Class<E> entityClazz;
	private final Class<DTO> dtoClazz;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@SuppressWarnings("unchecked")
	public AbstractRestController(CrudService<ID, E> crudService){
		this.crudService = crudService;

		this.entityClazz = (Class<E>) GenericTypeResolver.resolveTypeArguments(getClass(), AbstractRestController.class)[1];
		this.dtoClazz = (Class<DTO>) GenericTypeResolver.resolveTypeArguments(getClass(), AbstractRestController.class)[2];
	}
	
	
	@GetMapping
	public Page<DTO> getAll(Pageable pageable){
		Page<E> entities = crudService.findAll(pageable);
		List<DTO> dtos = entities.getContent()
				.stream()
				.map(entity -> convertToDto(entity))
				.collect(Collectors.toList());
		
		return new PageImpl<>(dtos);
	}

	@GetMapping("/{id}")
	public DTO findById(@PathVariable("id") ID id){
		E result = crudService.findOne(id);
		
		if(result == null){
			throw new ResourceNotFoundException();
		}
		
		return convertToDto(result);
	}
	
	@PostMapping
	public DTO create(@RequestBody DTO dto){
		E entity = convertToEntity(dto);		
		E savedEntity = crudService.save(entity);		
		return convertToDto(savedEntity);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable ID id){
		crudService.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DTO> update(@PathVariable ID id, @RequestBody DTO dto){
		if(!crudService.exists(id)){
			return ResponseEntity.notFound().build();
		}
		
		E entity =  convertToEntity(dto);		
		entity.setId(id);
		
		E updated = crudService.save(entity);		
		return ResponseEntity.ok(convertToDto(updated));
	}	
	
	
	protected DTO convertToDto(E entity) {
		return modelMapper.map(entity, dtoClazz);
	}
	
	protected E convertToEntity(DTO dto) {
		return modelMapper.map(dto, entityClazz);
	}
	
}
