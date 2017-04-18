package com.demo.sales;

import com.demo.sales.schemas.v3.BaseModel;
import com.demo.sales.schemas.v3.CustomerModel;
import com.demo.sales.schemas.v3.OrderModel;
import io.swagger.converter.ModelConverter;
import io.swagger.converter.ModelConverterContext;
import io.swagger.converter.ModelConverters;
import io.swagger.models.Model;
import io.swagger.models.properties.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class DiscriminatorTest {

    @Test
    public void testModelConvertor() throws IOException {
        ModelConverter converter = new ModelConverter() {
            @Override
            public Property resolveProperty(Type type, ModelConverterContext modelConverterContext, Annotation[] annotations, Iterator<ModelConverter> modelConverterIterator) {
                return null;
            }

            @Override
            public Model resolve(Type type, ModelConverterContext modelConverterContext, Iterator<ModelConverter> modelConverterIterator) {
                return null;
            }
        };
        ModelConverters.getInstance().addConverter(converter);

        // arrange
        String me = "testJacksonPolymorphism()";
        OrderModel v1OrderModel = new OrderModel();
        v1OrderModel.setOrderId("ONE");
        CustomerModel customerModel = new CustomerModel();
        customerModel.setLastName("TWO");
        v1OrderModel.setCustomer(customerModel);
        org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();

        // act
        String json = mapper.writeValueAsString(v1OrderModel);
        System.out.println(me + " " + json);
        BaseModel v1BaseModelLoadedFromJson = mapper.readValue(json, BaseModel.class);
        OrderModel v1OrderModelLoadedFromJson = (OrderModel) v1BaseModelLoadedFromJson;
        String json2 = mapper.writeValueAsString(v1OrderModelLoadedFromJson);
        System.out.println(me + " " + json2);

        // assert
        assertEquals(v1OrderModel.getOrderId(), v1OrderModelLoadedFromJson.getOrderId());
        assertEquals(
                v1OrderModel.getCustomer().getLastName(),
                v1OrderModelLoadedFromJson.getCustomer().getLastName());
    }
}
