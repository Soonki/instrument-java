package com.demo.sales.schemas.v4;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "TransactionServicesRequestType",
        propOrder = {"orderId", "customer", "lastName"}
)
@ApiModel(discriminator = "@class", value = "com.demo.sales.schemas.v4.OrderModel")
public class OrderModel extends com.demo.sales.schemas.v4.BaseModel implements Serializable {

    @XmlElement(namespace = "", required = true, nillable = true, name = "@class")
    @ApiModelProperty(allowableValues = "com.demo.sales.schemas.v4.OrderModel")
    @JsonProperty("@class")
    protected String className;

    private String orderId;
    private CustomerModel customer;
    private String lastName;
    private CarModel carModel;

    public OrderModel() {
    }

    public OrderModel(String lastName) {
        super();
        this.lastName = lastName;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
