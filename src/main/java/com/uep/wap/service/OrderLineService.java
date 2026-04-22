package com.uep.wap.service;

import com.uep.wap.dto.OrderLineDTO;
import com.uep.wap.model.OrderLine;
import com.uep.wap.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService {

    @Autowired
    private static OrderLineRepository orderLineRepository;

    public void addOrderLine(OrderLineDTO orderLineDTO) {
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(orderLineDTO.getQuantity());
        orderLineRepository.save(orderLine);
        System.out.println("Quantity changed");
    }

    public static Iterable<OrderLine> getAllOrderLines() { return orderLineRepository.findAll(); }

}