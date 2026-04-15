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

    public Date getOrderDate() { return orderDate; }

    public String getStatus() { return status; }

    public PaymentStatus getPaymentStatus() { return paymentStatus; }

    public User getCustomer() { return customer; }

    public List<OrderLine> getOrderLines() { return orderLines; }

    public Invoice getInvoice() { return invoice; }



}



