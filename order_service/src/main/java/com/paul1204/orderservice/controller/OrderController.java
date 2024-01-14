package com.paul1204.orderservice.controller;
import com.paul1204.orderservice.dto.OrderRequest;

import com.paul1204.orderservice.model.Order;
import com.paul1204.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAll(){
        return orderService.getAll();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@RequestBody Long id){
        orderService.delete(id);
        return "Deleted!";
    }
}
