package com.demo.sales;

import com.demo.sales.schemas.v3.CustomerModel;
import com.demo.sales.schemas.v3.OrderModel;
import com.demo.sales.schemas.v3.ServiceResponse;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class DozerTest {

    @Autowired DozerBeanMapper dozerBeanMapper;

    @Test
    public void testCopyBean() {
        String me = "testCopyBean()";

        OrderModel orderModelV3 = new OrderModel();
        orderModelV3.setOrderId("ONE");
        CustomerModel customerModelV3 = new CustomerModel();
        customerModelV3.setLastName("ONE");
        orderModelV3.setCustomer(customerModelV3);
        ServiceResponse serviceResponseV3 = new ServiceResponse();
        serviceResponseV3.getModels().add(orderModelV3);
        serviceResponseV3.getModels().add(customerModelV3);

        System.out.println(me + " " + ReflectionToStringBuilder.toString(serviceResponseV3));
        com.demo.sales.schemas.v4.ServiceResponse serviceResponseV4 =
                dozerBeanMapper.map(
                        serviceResponseV3, com.demo.sales.schemas.v4.ServiceResponse.class);
        System.out.println(me + " " + ReflectionToStringBuilder.toString(serviceResponseV4));
    }
}
