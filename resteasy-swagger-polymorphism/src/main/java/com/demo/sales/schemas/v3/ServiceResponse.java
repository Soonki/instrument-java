package com.demo.sales.schemas.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@ApiModel(discriminator = "@class", value = "com.demo.sales.schemas.v3.ServiceResponse")
public class ServiceResponse implements Serializable {
    boolean success;
    List<BaseModel> models;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<BaseModel> getModels() {
        if (models == null) this.models = new ArrayList<>();
        return models;
    }

    public void setModels(List<BaseModel> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
