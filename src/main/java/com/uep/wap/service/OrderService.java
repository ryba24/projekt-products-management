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

    public OrderDTO getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return toDTO(order);
    }

    public OrderDTO create(OrderDTO dto) {
        Order order = new Order();
        order.setOrderDate(dto.getOrderDate() != null ? dto.getOrderDate() : new Date());
        order.setStatus(dto.getStatus());
        order.setPaymentStatus(dto.getPaymentStatus());

        if (dto.getCustomerId() != null) {
            User customer = userRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getCustomerId()));
            order.setCustomer(customer);
        }

        Order saved = orderRepository.save(order);

        if (dto.getOrderLines() != null) {
            for (OrderLineDTO lineDTO : dto.getOrderLines()) {
                Product product = productRepository.findById(lineDTO.getProductId())
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

        return toDTO(orderRepository.findById(saved.getId()).orElse(saved));
    }

    public OrderDTO update(Long id, OrderDTO dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        if (dto.getStatus()        != null) order.setStatus(dto.getStatus());
        if (dto.getPaymentStatus() != null) order.setPaymentStatus(dto.getPaymentStatus());
        if (dto.getOrderDate()     != null) order.setOrderDate(dto.getOrderDate());

        if (dto.getCustomerId() != null) {
            User customer = userRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getCustomerId()));
            order.setCustomer(customer);
        }

        // If order lines are provided, replace them entirely
        if (dto.getOrderLines() != null) {
            // Delete existing lines
            if (order.getOrderLines() != null) {
                for (OrderLine existing : order.getOrderLines()) {
                    orderLineRepository.deleteById(existing.getId());
                }
            }
            // Save new lines
            List<OrderLine> newLines = new ArrayList<>();
            for (OrderLineDTO lineDTO : dto.getOrderLines()) {
                Product product = productRepository.findById(lineDTO.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found with id: " + lineDTO.getProductId()));
                OrderLine line = new OrderLine();
                line.setQuantity(lineDTO.getQuantity());
                line.setUnitPrice(lineDTO.getUnitPrice());
                line.setSubtotal(lineDTO.getSubtotal());
                line.setOrder(order);
                line.setProduct(product);
                newLines.add(orderLineRepository.save(line));
            }
            order.setOrderLines(newLines);
        }

        return toDTO(orderRepository.save(order));
    }

    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
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
                if (line.getProduct() != null) {
                    lineDTO.setProductId(line.getProduct().getId());
                }
                lineDTOs.add(lineDTO);
            }
        }
        dto.setOrderLines(lineDTOs);
        return dto;
    }
}