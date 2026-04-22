package com.uep.wap.service;

import com.uep.wap.dto.OrderDTO;
import com.uep.wap.dto.OrderLineDTO;
import com.uep.wap.model.Order;
import com.uep.wap.model.OrderLine;
import com.uep.wap.model.Product;
import com.uep.wap.model.User;
import com.uep.wap.repository.OrderLineRepository;
import com.uep.wap.repository.OrderRepository;
import com.uep.wap.repository.ProductRepository;
import com.uep.wap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<OrderDTO> getAll() {
        List<OrderDTO> result = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            result.add(toDTO(order));
        }
        return result;
    }

    public OrderDTO create(OrderDTO dto) {
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setStatus(dto.getStatus());
        order.setPaymentStatus(dto.getPaymentStatus());

        if (dto.getCustomerId() != null) {
            User customer = userRepository.findById(Math.toIntExact(dto.getCustomerId()))
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getCustomerId()));
            order.setCustomer(customer);
        }

        Order saved = orderRepository.save(order);

        if (dto.getOrderLines() != null) {
            for (OrderLineDTO lineDTO : dto.getOrderLines()) {
                Product product = productRepository.findById(Math.toIntExact(lineDTO.getProductId()))
                        .orElseThrow(() -> new RuntimeException("Product not found with id: " + lineDTO.getProductId()));
                OrderLine line = new OrderLine();
                line.setQuantity(lineDTO.getQuantity());
                line.setUnitPrice(lineDTO.getUnitPrice());
                line.setSubtotal(lineDTO.getSubtotal());
                line.setOrder(saved);
                line.setProduct(product);
                orderLineRepository.save(line);
            }
        }

        return toDTO(saved);
    }

    private OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setPaymentStatus(order.getPaymentStatus());
        if (order.getCustomer() != null) {
            dto.setCustomerId(order.getCustomer().getId());
        }

        List<OrderLineDTO> lineDTOs = new ArrayList<>();
        if (order.getOrderLines() != null) {
            for (OrderLine line : order.getOrderLines()) {
                OrderLineDTO lineDTO = new OrderLineDTO();
                lineDTO.setId(line.getId());
                lineDTO.setQuantity(line.getQuantity());
                lineDTO.setUnitPrice(line.getUnitPrice());
                lineDTO.setSubtotal(line.getSubtotal());
                lineDTO.setOrderId(order.getId());
                lineDTO.setProductId(line.getProduct().getId());
                lineDTOs.add(lineDTO);
            }
        }
        dto.setOrderLines(lineDTOs);
        return dto;
    }
}