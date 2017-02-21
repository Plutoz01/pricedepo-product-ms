package org.plutoz.pricedepo.product.ms.rest.dto;

import java.math.BigDecimal;

import org.plutoz.pricedepo.common.domain.Identifiable;

import lombok.Data;

@Data
public class ProductDTO implements Identifiable<Long> {
	private Long id;
	private Long brandId;
	private Long manufacturerId;
	private String fullName;
	private BigDecimal unit;
	private String measurement;
}
