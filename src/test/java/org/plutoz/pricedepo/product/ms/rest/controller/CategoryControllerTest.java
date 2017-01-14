package org.plutoz.pricedepo.product.ms.rest.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.plutoz.pricedepo.product.ms.domain.Category;
import org.plutoz.pricedepo.product.ms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CategoryController.class)
public class CategoryControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CategoryService categoryService;
	
	@Test
	public void getAllTest() throws Exception {
		Iterable<Category> testData = getCategories(30);
		
		when(categoryService.findAll()).thenReturn(testData);
		
		this.mvc.perform(get("/categories?page=0&size=3").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		
		verify(categoryService, times(1)).findAll();
		verifyNoMoreInteractions(categoryService);
	}
	
	private Iterable<Category> getCategories(int count){
		List<Category> result = new ArrayList<>();
		
		for(int i=1; i <= count; i++){
			result.add(new Category( (long) i, "Category " + i));
		}
		
		return result;
	}
	
}
