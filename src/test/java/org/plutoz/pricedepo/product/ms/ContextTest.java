package org.plutoz.pricedepo.product.ms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.plutoz.pricedepo.product.ms.repository.CategoryRepository;
import org.plutoz.pricedepo.product.ms.rest.controller.CategoryController;
import org.plutoz.pricedepo.product.ms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContextTest {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired 
	CategoryController categoryController;

	@Test
	public void contextLoads() {
	}

}
