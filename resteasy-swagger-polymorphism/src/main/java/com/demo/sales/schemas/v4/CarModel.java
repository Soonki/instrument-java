package com.demo.sales.schemas.v4;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@ApiModel(discriminator = "@class", value = "com.demo.sales.schemas.v4.CarModel")
public class CarModel extends BaseModel implements Serializable {

    @XmlElement(namespace = "", required = true, nillable = true, name = "@class")
    @ApiModelProperty(allowableValues = "com.demo.sales.schemas.v4.CarModel")
    @JsonProperty("@class")
    protected String className;

    private String id;
    private WheelModel wheelModel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WheelModel getWheelModel() {
        return wheelModel;
    }

    public void setWheelModel(WheelModel wheelModel) {
        this.wheelModel = wheelModel;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
