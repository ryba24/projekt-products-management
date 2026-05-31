package com.uep.wap.service;

import com.uep.wap.dto.InvoiceDTO;
import com.uep.wap.model.Invoice;
import com.uep.wap.model.Order;
import com.uep.wap.repository.InvoiceRepository;
import com.uep.wap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<InvoiceDTO> getAll() {
        List<InvoiceDTO> result = new ArrayList<>();
        for (Invoice invoice : invoiceRepository.findAll()) {
            result.add(toDTO(invoice));
        }
        return result;
    }

    public InvoiceDTO getById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
        return toDTO(invoice);
    }

    public InvoiceDTO create(InvoiceDTO dto) {
        Invoice invoice = new Invoice();
        invoice.setIssueDate(dto.getIssueDate() != null ? dto.getIssueDate() : new Date());
        invoice.setTotalAmount(dto.getTotalAmount());

        if (dto.getOrderId() != null) {
            Order order = orderRepository.findById(dto.getOrderId())
                    .orElseThrow(() -> new RuntimeException("Order not found with id: " + dto.getOrderId()));
            invoice.setOrder(order);
        }

        return toDTO(invoiceRepository.save(invoice));
    }

    public InvoiceDTO update(Long id, InvoiceDTO dto) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));

        if (dto.getIssueDate() != null) invoice.setIssueDate(dto.getIssueDate());
        if (dto.getTotalAmount() != null) invoice.setTotalAmount(dto.getTotalAmount());

        if (dto.getOrderId() != null) {
            Order order = orderRepository.findById(dto.getOrderId())
                    .orElseThrow(() -> new RuntimeException("Order not found with id: " + dto.getOrderId()));
            invoice.setOrder(order);
        }

        return toDTO(invoiceRepository.save(invoice));
    }

    public void delete(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new RuntimeException("Invoice not found with id: " + id);
        }
        invoiceRepository.deleteById(id);
    }

    private InvoiceDTO toDTO(Invoice invoice) {
        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(invoice.getId());
        dto.setIssueDate(invoice.getIssueDate());
        dto.setTotalAmount(invoice.getTotalAmount());
        if (invoice.getOrder() != null) {
            dto.setOrderId(invoice.getOrder().getId());
        }
        return dto;
    }
}
