package com.uep.wap.controller;

import com.uep.wap.model.OrderLine;
import com.uep.wap.service.OrderLineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class OrderLineController {

    private final OrderLineService orderLineService;

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @GetMapping(path = "/orderLines")
    public Iterable<OrderLine> getAllOrderLines(){
        return orderLineService.getAllOrderLines();
    }


}
