package com.demo.sales.swagger;

import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.util.Json;

public class JaxbEnabledBeanConfig extends BeanConfig {
    public JaxbEnabledBeanConfig() {
        super();
        Json.mapper().registerModule(new JaxbAnnotationModule());
    }
}
