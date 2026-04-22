package com.uep.wap.dto;

import com.uep.wap.model.PaymentStatus;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    private Long id;
    private Date orderDate;
    private String status;
    private PaymentStatus paymentStatus;
    private Long customerId;
    private List<OrderLineDTO> orderLines;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public List<OrderLineDTO> getOrderLines() { return orderLines; }
    public void setOrderLines(List<OrderLineDTO> orderLines) { this.orderLines = orderLines; }
}