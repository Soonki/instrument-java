package com.demo.sales.schemas.v3;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "BaseModelType",
        propOrder = {"version", "className"}
)
@XmlSeeAlso({OrderModel.class, CustomerModel.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class", include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OrderModel.class),
        @JsonSubTypes.Type(value = CustomerModel.class),
        @JsonSubTypes.Type(value = CarModel.class),
        @JsonSubTypes.Type(value = WheelModel.class),
})
@ApiModel(value = "com.demo.sales.schemas.v3.BaseModel")
@JsonIgnoreProperties({"@class"})
public abstract class BaseModel {
    private static final long serialVersionUID = 1L;

    protected int version;

    @XmlElement(namespace = "", required = true, nillable = true, name = "@class")
    @ApiModelProperty(allowableValues = "com.demo.sales.schemas.v3.BaseModel")
    @JsonProperty("@class")
    protected String className;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
