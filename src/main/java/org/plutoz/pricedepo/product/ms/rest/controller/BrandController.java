package org.plutoz.pricedepo.product.ms.rest.controller;

import org.plutoz.pricedepo.common.rest.controller.AbstractRestController;
import org.plutoz.pricedepo.product.ms.domain.Brand;
import org.plutoz.pricedepo.product.ms.rest.dto.BrandDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brands")
public class BrandController extends AbstractRestController<Long, Brand, BrandDto> {
}
