package org.plutoz.pricedepo.product.ms.rest.dto;

import org.plutoz.pricedepo.common.domain.Identifiable;

import lombok.Data;

@Data
public class BrandDto implements Identifiable<Long> {
	private Long id;
	private String name;
	private String logoUrl;
	private Long ownerId;
}
