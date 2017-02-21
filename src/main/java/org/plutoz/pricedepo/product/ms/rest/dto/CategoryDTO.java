package org.plutoz.pricedepo.product.ms.rest.dto;

import org.plutoz.pricedepo.common.domain.Identifiable;

import lombok.Data;

@Data
public class CategoryDTO implements Identifiable<Long> {
	private Long id;
	private String name;
}
