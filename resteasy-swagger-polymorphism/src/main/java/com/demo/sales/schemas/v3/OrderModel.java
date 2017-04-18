package com.demo.sales.schemas.v3;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@ApiModel(value = "com.demo.sales.schemas.v3.OrderModel")
public class OrderModel extends com.demo.sales.schemas.v3.BaseModel implements Serializable {
    @XmlElement(namespace = "", required = true, nillable = true, name = "@class")
    @ApiModelProperty(allowableValues = "com.demo.sales.schemas.v3.OrderModel")
    @JsonProperty("@class")
    private String className;

    private String orderId;
    private CustomerModel customer;
    private String name;
    private CarModel carModel;

    public OrderModel() {
    }

    public OrderModel(String name) {
        super();
        this.name = name;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarModel getCar() {
        return carModel;
    }

    public void setCar(CarModel carModel) {
        this.carModel = carModel;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
