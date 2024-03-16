package com.paul1204.orderservice.controller;
import com.paul1204.orderservice.dto.OrderRequest;

import com.paul1204.orderservice.model.Order;
import com.paul1204.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
    //public String placeOrder(@RequestBody OrderRequest orderRequest){
        return CompletableFuture.supplyAsync(() ->orderService.placeOrder(orderRequest));
        //return orderService.placeOrder(orderRequest);
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Something went Wrong! Please Try again");
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
