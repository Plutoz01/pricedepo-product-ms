package org.plutoz.pricedepo.product.ms.rest.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.plutoz.pricedepo.common.rest.exception.ResourceNotFoundException;
import org.plutoz.pricedepo.product.ms.domain.Category;
import org.plutoz.pricedepo.product.ms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@EnableSpringDataWebSupport
@WebMvcTest(controllers = CategoryController.class)
public class CategoryControllerTest {
	
	private final static int TEST_CATEGORY_COUNT = 30;
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CategoryService categoryService;
	 
	@Test
	public void findByNonExistingIdTest() throws Exception{
		when(categoryService.findOne((Long)notNull()))
			.thenThrow(new ResourceNotFoundException());
		
		this.mvc.perform(get("/categories/123")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
		
		verify(categoryService, times(1)).findOne((Long)notNull());
		verifyNoMoreInteractions(categoryService);
	}
	
	@Test
	public void getAllTest() throws Exception {
		Page<Category> findResult = new PageImpl<Category>(getCategories(TEST_CATEGORY_COUNT));
		
		when(categoryService.findAll((Pageable)notNull()))
			.thenReturn(findResult);
		
		this.mvc.perform(get("/categories")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.content.length()", equalTo(TEST_CATEGORY_COUNT)))
			.andExpect(jsonPath("$.totalElements", equalTo(TEST_CATEGORY_COUNT)))
			.andExpect(jsonPath("$.number", equalTo(0)))
			.andExpect(jsonPath("$.first", equalTo(true)))
			.andExpect(jsonPath("$.sort", nullValue()))
			.andExpect(jsonPath("$.numberOfElements", equalTo(TEST_CATEGORY_COUNT)));
		
		verify(categoryService, times(1)).findAll((Pageable)notNull());
		verifyNoMoreInteractions(categoryService);
	}
	
	@Test
	public void searchByNameTest() throws Exception{
		final String searchPhrase = "1";
		Page<Category> searchResult = new PageImpl<>(
				getCategories(TEST_CATEGORY_COUNT).stream()
					.filter(c -> c.getName() != null && c.getName().contains(searchPhrase))
					.collect(Collectors.toList())
		); 
		
		when(categoryService.findByNameContainingIgnoreCase((String) notNull(), (Pageable)notNull()))
			.thenReturn(searchResult);
		
		this.mvc.perform(get("/categories/search/name/" + searchPhrase)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content.length()", equalTo(searchResult.getContent().size())))
				.andExpect(jsonPath("$.totalElements", equalTo(searchResult.getContent().size())))
				.andExpect(jsonPath("$.first", equalTo(true)))
				.andExpect(jsonPath("$.sort", nullValue()))
				.andExpect(jsonPath("$.numberOfElements", equalTo(searchResult.getContent().size())));

		verify(categoryService, times(1)).findByNameContainingIgnoreCase((String) notNull(), (Pageable)notNull());
		verifyNoMoreInteractions(categoryService);
	}
	
	@Test
	public void searchByNameButNotFoundTest() throws Exception {
//		when(categoryService.findByNameContainingIgnoreCase((String) notNull(), (Pageable)notNull()))
//		.thenReturn(searchResult);
	}
	
	
	private List<Category> getCategories(int count){
		List<Category> result = new ArrayList<>();
		
		for(int i=1; i <= count; i++){
			result.add(new Category( (long) i, "Category " + i));
		}
		
		return result;
	}
	
}
