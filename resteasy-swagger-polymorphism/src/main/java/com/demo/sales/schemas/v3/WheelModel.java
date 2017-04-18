package com.demo.sales.schemas.v3;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@ApiModel(value = "com.demo.sales.schemas.v3.WheelModel")
public class WheelModel extends BaseModel implements Serializable {
    @XmlElement(namespace = "", required = true, nillable = true, name = "@class")
    @ApiModelProperty(allowableValues = "com.demo.sales.schemas.v3.WheelModel")
    @JsonProperty("@class")
    private String className;

    private String id;

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
