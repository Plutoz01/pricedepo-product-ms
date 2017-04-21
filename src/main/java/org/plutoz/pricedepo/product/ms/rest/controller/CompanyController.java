package org.plutoz.pricedepo.product.ms.rest.controller;

import org.plutoz.pricedepo.common.rest.controller.AbstractRestController;
import org.plutoz.pricedepo.product.ms.domain.Company;
import org.plutoz.pricedepo.product.ms.rest.dto.CompanyDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController extends AbstractRestController<Long, Company, CompanyDto>{
}
