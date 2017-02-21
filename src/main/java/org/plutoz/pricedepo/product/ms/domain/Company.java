package org.plutoz.pricedepo.product.ms.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.plutoz.pricedepo.common.domain.Identifiable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Identifiable<Long> {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique=true)
	private String name;
	
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private Set<Brand> brands = new HashSet<>();
	
	@OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
	private Set<Product> products = new HashSet<>();
}
