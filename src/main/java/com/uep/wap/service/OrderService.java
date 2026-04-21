package com.uep.wap.service;

import com.uep.wap.dto.OrderDTO;
import com.uep.wap.model.Order;
import com.uep.wap.repository.OrderLineRepository;
import com.uep.wap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private static OrderRepository orderRepository;

    public void addOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setStatus(orderDTO.getStatus());
        orderRepository.save(order);
        System.out.println("Status checked");
    }

    public static Iterable<Order> getAllOrders() { return orderRepository.findAll(); }

}