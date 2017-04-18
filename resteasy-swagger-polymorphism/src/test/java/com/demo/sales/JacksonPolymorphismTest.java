package com.demo.sales;

import static junit.framework.TestCase.assertEquals;

import com.demo.sales.schemas.v3.BaseModel;
import com.demo.sales.schemas.v3.CustomerModel;
import com.demo.sales.schemas.v3.OrderModel;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class JacksonPolymorphismTest {

    @Test
    public void testJacksonPolymorphism() throws IOException {
        // arrange
        String me = "testJacksonPolymorphism()";
        OrderModel v1OrderModel = new OrderModel();
        v1OrderModel.setOrderId("ONE");
        CustomerModel customerModel = new CustomerModel();
        customerModel.setLastName("TWO");
        v1OrderModel.setCustomer(customerModel);
        ObjectMapper mapper = new ObjectMapper();

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
