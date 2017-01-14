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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String manufacturer;
	
	@Column(nullable = false)
	private String brand;
	
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
