package com.uep.wap.service;

import com.uep.wap.dto.OrderDTO;
import com.uep.wap.model.Order;
import com.uep.wap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository OrderRepository;

    public void addOrder(OrderDTO OrderDTO) {
        Order Order = new Order();
        Order.getStatus(OrderDTO.getStatus());
        OrderRepository.save(Order);
        System.out.println("Status checked");
    }

    public Iterable<Order> getAllOrders() { return OrderRepository.findAll(); }

}