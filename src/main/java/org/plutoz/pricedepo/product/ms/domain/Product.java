package org.plutoz.pricedepo.product.ms.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.plutoz.pricedepo.common.domain.Identifiable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product implements Identifiable<Long> {

	@Id
	@GeneratedValue()
	private Long id;
	
	@ManyToOne
	private Brand brand;
	
	@ManyToOne
	private Company manufacturer;

	
	@Column(nullable = false)
	private String fullName;
	
	@ManyToMany
	@JoinTable
	private Set<Category> categories = new HashSet<>();
	
	@Column(nullable = false)
	private BigDecimal unit;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Measurement measurement; 
}
