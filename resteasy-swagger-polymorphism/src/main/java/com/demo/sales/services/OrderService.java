package com.demo.sales.services;

import com.demo.sales.schemas.v3.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderService {

    static Map<Integer, BaseModel> data =
            new HashMap<Integer, BaseModel>() {
                {
                    put(1, new OrderModel("One"));
                    put(2, new OrderModel("Two"));
                }
            };

    public com.demo.sales.schemas.v3.ServiceResponse getOrders() {
        com.demo.sales.schemas.v3.ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setSuccess(true);
        serviceResponse.getModels().add(buildTestOrder());
        return serviceResponse;
    }

    public ServiceResponse updateOrder(OrderModel orderModel) {
        com.demo.sales.schemas.v3.ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setSuccess(true);
        serviceResponse.getModels().add(orderModel);
        return serviceResponse;
    }

    private OrderModel buildTestOrder() {
        OrderModel orderModel = new OrderModel();
        orderModel.setName("One");
        orderModel.setOrderId("Two");
        orderModel.setCustomer(buildTestCustomer());
        orderModel.setCar(buildTestCar());
        return orderModel;
    }

    private CarModel buildTestCar() {
        CarModel carModel = new CarModel();
        carModel.setId("Four");
        carModel.setWheelModel(buildTestFeeCode());
        return carModel;
    }

    private WheelModel buildTestFeeCode() {
        WheelModel wheelModel = new WheelModel();
        wheelModel.setId("Five");
        return wheelModel;
    }

    private CustomerModel buildTestCustomer() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setLastName("Three");
        customerModel.setVersion(1);
        return customerModel;
    }
}
