package com.uep.wap.dto;

import com.uep.wap.model.Invoice;
import com.uep.wap.model.OrderLine;
import com.uep.wap.model.PaymentStatus;
import com.uep.wap.model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class OrderDTO {

    private Date orderDate;
    private String status;
    private PaymentStatus paymentStatus;
    private User customer;
    private List<OrderLine> orderLines;
    private Invoice invoice;


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}



