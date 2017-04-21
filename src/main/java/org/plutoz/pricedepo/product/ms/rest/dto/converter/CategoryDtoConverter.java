package org.plutoz.pricedepo.product.ms.rest.dto.converter;

import org.plutoz.pricedepo.common.rest.dto.converter.AbstractDtoConverter;
import org.plutoz.pricedepo.product.ms.domain.Category;
import org.plutoz.pricedepo.product.ms.rest.dto.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoConverter extends AbstractDtoConverter<CategoryDto, Category> {
}
