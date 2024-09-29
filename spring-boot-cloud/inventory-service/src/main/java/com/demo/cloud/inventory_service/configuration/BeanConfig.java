package com.demo.cloud.inventory_service.configuration;

import com.demo.cloud.inventory_service.client.supplier.FirstSupplierClient;
import com.demo.cloud.inventory_service.client.supplier.SecondSupplierClient;
import com.demo.cloud.inventory_service.client.supplier.SupplierClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import static com.demo.cloud.inventory_service.constant.SupplierName.FIRST_SUPPLIER;
import static com.demo.cloud.inventory_service.constant.SupplierName.SECOND_SUPPLIER;

@Configuration
@EnableConfigurationProperties(BusinessPropertiesConfiguration.class)
public class BeanConfig {

    private final BusinessPropertiesConfiguration businessPropertiesConfiguration;

    public BeanConfig(final BusinessPropertiesConfiguration businessPropertiesConfiguration) {
        this.businessPropertiesConfiguration = businessPropertiesConfiguration;
    }

    @Bean
    SupplierClient supplierClient() {
        if(FIRST_SUPPLIER.equalsIgnoreCase(businessPropertiesConfiguration.getSupplierName())) {
            return new FirstSupplierClient();
        } else if(SECOND_SUPPLIER.equalsIgnoreCase(businessPropertiesConfiguration.getSupplierName())) {
            return new SecondSupplierClient();
        } else {
            throw new IllegalStateException("SUPPLIER NAME NOT CONFIGURED");
        }
    }
}
