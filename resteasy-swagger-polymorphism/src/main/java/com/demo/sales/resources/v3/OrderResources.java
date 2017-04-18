package com.demo.sales.resources.v3;

import com.demo.sales.schemas.v3.OrderModel;
import com.demo.sales.schemas.v3.ServiceResponse;
import com.demo.sales.services.OrderService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service(value = OrderResources.BEAN_NAME)
@javax.ws.rs.Path("/v3/orders")
@Api(value = "orderResources", description = "Order Resources v3")
public class OrderResources {
    static final String BEAN_NAME = "com.demo.sales.resources.v3.OrderResources";

    @Autowired
    OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses({
            @ApiResponse(code = 200, response = ServiceResponse.class, message = "success")
    })
    public Response getOrders() {
        com.demo.sales.schemas.v3.ServiceResponse serviceResponseV3 = orderService.getOrders();
        return Response.ok(serviceResponseV3).build();
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
            @ApiParam(value = "Order Model v3")
            OrderModel orderModelV3) {
        System.out.println("Received [" + ReflectionToStringBuilder.toString(orderModelV3) + "]");
        com.demo.sales.schemas.v3.ServiceResponse serviceResponseV3 = orderService.updateOrder(orderModelV3);
        return Response.ok(serviceResponseV3).build();
    }
}
