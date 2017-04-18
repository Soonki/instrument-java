package com.demo.sales.schemas.v3;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@ApiModel(discriminator = "@class", value = "com.demo.sales.schemas.v3.CustomerModel")
public class CustomerModel extends com.demo.sales.schemas.v3.BaseModel implements Serializable {
    @XmlElement(namespace = "", required = true, nillable = true, name = "@class")
    @ApiModelProperty(allowableValues = "com.demo.sales.schemas.v3.CustomerModel")
    @JsonProperty("@class")
    private String className;

    private String lastName;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
