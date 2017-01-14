package org.plutoz.pricedepo.product.ms;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Configuration
@EnableEurekaClient
public class ServiceDiscoveryConfiguration {

}