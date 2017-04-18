package com.demo.sales.resources.v4;

import com.demo.sales.schemas.v3.OrderModel;
import com.demo.sales.schemas.v4.ServiceResponse;
import com.demo.sales.services.OrderService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service(value = OrderResources.BEAN_NAME)
@javax.ws.rs.Path("/v4/orders")
@Api(value = "OrderResources", description = "Order Resources v4")
public class OrderResources {
    static final String BEAN_NAME = "com.demo.sales.resources.v4.OrderResources";

    @Autowired
    OrderService orderServiceV3;

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get an order", notes = "Get an order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = ServiceResponse.class, message = "success"),
            @ApiResponse(code = 400, response = ServiceResponse.class, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, response = ServiceResponse.class, message = "Not found")}
    )
    public Response getOrders() {
        com.demo.sales.schemas.v3.ServiceResponse serviceResponseV3 = orderServiceV3.getOrders();
        com.demo.sales.schemas.v4.ServiceResponse serviceResponseV4 = dozerBeanMapper.map(serviceResponseV3, com.demo.sales.schemas.v4.ServiceResponse.class);
        return Response.ok(serviceResponseV4).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Update an order", notes = "Update an order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = ServiceResponse.class, message = "success"),
            @ApiResponse(code = 400, response = ServiceResponse.class, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, response = ServiceResponse.class, message = "Not found")}
    )
    public Response updateOrder(
            @ApiParam(value = "Order Model v4")
            com.demo.sales.schemas.v4.OrderModel orderModelV4) {
        System.out.println("Received [" + ReflectionToStringBuilder.toString(orderModelV4) + "]");
        OrderModel orderModelV3 = dozerBeanMapper.map(orderModelV4, OrderModel.class);
        com.demo.sales.schemas.v3.ServiceResponse serviceResponseV3 = orderServiceV3.updateOrder(orderModelV3);
        com.demo.sales.schemas.v4.ServiceResponse serviceResponseV4 = dozerBeanMapper.map(serviceResponseV3, com.demo.sales.schemas.v4.ServiceResponse.class);
        return Response.ok(serviceResponseV4).build();
    }
}
