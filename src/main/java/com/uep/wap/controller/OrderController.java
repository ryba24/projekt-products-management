package com.uep.wap.controller;

import com.uep.wap.dto.OrderDTO;
import com.uep.wap.model.Order;
import com.uep.wap.model.Order;
import com.uep.wap.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    @GetMapping(path = "/orders")
//    public Iterable<Order> getAllOrders(){
//        return orderService.getAllOrders();
//    }


}
