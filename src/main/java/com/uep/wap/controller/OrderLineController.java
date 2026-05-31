package com.uep.wap.controller;

import com.uep.wap.dto.OrderLineDTO;
import com.uep.wap.service.OrderLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class OrderLineController {

    private final OrderLineService orderLineService;

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @GetMapping(path = "/orderLines")
    public ResponseEntity<List<OrderLineDTO>> getAllOrderLines() {
        return ResponseEntity.ok(orderLineService.getAllOrderLines());
    }
}
