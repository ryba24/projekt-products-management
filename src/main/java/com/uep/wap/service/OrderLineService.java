package com.uep.wap.service;

import com.uep.wap.dto.OrderLineDTO;
import com.uep.wap.model.OrderLine;
import com.uep.wap.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderLineService {

    @Autowired
    private OrderLineRepository orderLineRepository;

    public List<OrderLineDTO> getAllOrderLines() {
        List<OrderLineDTO> result = new ArrayList<>();
        for (OrderLine line : orderLineRepository.findAll()) {
            result.add(toDTO(line));
        }
        return result;
    }

    public void addOrderLine(OrderLineDTO orderLineDTO) {
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(orderLineDTO.getQuantity());
        orderLineRepository.save(orderLine);
    }

    private OrderLineDTO toDTO(OrderLine line) {
        OrderLineDTO dto = new OrderLineDTO();
        dto.setId(line.getId());
        dto.setQuantity(line.getQuantity());
        dto.setUnitPrice(line.getUnitPrice());
        dto.setSubtotal(line.getSubtotal());
        if (line.getOrder()   != null) dto.setOrderId(line.getOrder().getId());
        if (line.getProduct() != null) dto.setProductId(line.getProduct().getId());
        return dto;
    }
}
