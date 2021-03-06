package org.plutoz.pricedepo.product.ms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.plutoz.pricedepo.common.domain.Identifiable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Identifiable<Long> {

	@Id
	@GeneratedValue
	private Long id;
		
	@Column(nullable = false, unique=true)
	private String name;
}
