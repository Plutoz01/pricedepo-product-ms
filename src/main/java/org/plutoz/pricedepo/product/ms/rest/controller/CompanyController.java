package org.plutoz.pricedepo.product.ms.rest.controller;

import org.plutoz.pricedepo.product.ms.domain.Company;
import org.plutoz.pricedepo.product.ms.rest.dto.CompanyDTO;
import org.plutoz.pricedepo.product.ms.service.CompanyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController extends AbstractRestController<Long, Company, CompanyDTO>{
	
	public CompanyController(CompanyService companyService) {
		super(companyService);
	}
}
