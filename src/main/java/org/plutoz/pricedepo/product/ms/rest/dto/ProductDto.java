package org.plutoz.pricedepo.product.ms.rest.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.plutoz.pricedepo.common.domain.Identifiable;

import lombok.Data;

@Data
public class ProductDto implements Identifiable<Long> {
	private Long id;
	private Long brandId;
	private Long manufacturerId;
	private String fullName;
	private BigDecimal unit;
	private String measurement;
	private Set<String> categories = new HashSet<>();
}
