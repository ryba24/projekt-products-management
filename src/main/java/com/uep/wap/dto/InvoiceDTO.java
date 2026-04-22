package com.uep.wap.dto;

import java.util.Date;

public class InvoiceDTO {

    private Long id;
    private Date issueDate;
    private Double totalAmount;
    private Long orderId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getIssueDate() { return issueDate; }
    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
}