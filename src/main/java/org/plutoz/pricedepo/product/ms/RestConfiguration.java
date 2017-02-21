package org.plutoz.pricedepo.product.ms;

import org.modelmapper.ModelMapper;
import org.plutoz.pricedepo.common.rest.exception.DefaultRestExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(DefaultRestExceptionHandler.class)
public class RestConfiguration {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
