package org.plutoz.pricedepo.product.ms.repository;

import org.plutoz.pricedepo.product.ms.domain.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
