package org.plutoz.pricedepo.product.ms.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.plutoz.pricedepo.common.domain.Identifiable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand implements Identifiable<Long> {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String logoUrl;
	
	@ManyToOne
	private Company owner;
	
	@OneToMany(mappedBy = "brand")
	private Set<Product> products = new HashSet<>();
}
