package com.hil.basiccrud.controllers;

import com.hil.basiccrud.entity.dto.OrderDto;
import com.hil.basiccrud.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/data/order")
public class OrderController extends AbstractController{

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/all")
    public Set<OrderDto> getAllOrders(){
        return orderService.getAll();
    }

    @GetMapping(value = "/getByUserId/{userId}")
    public Set<OrderDto> getOrderByUserId(@PathVariable Long userId) {
        return orderService.getOrderById(userId);
    }

    @PutMapping()
    public OrderDto updateOrder(@RequestBody OrderDto orderDto){
        return orderService.updateOrder(orderDto);
    }

    @PostMapping(value = "/create")
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @DeleteMapping(value = "/{orderId}")
    public Boolean deleteOrder(@PathVariable Long orderId) {
        return orderService.deleteOrder(orderId);
    }
}
