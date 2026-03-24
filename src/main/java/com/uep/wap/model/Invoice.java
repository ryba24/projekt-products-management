package com.uep.wap.model;

import javax.persistence.*;

@Entity
@Table(name="invoice")
public class Invoice {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//
    private long id;
    @Column(name ="invoiceAmount")
    private long invoiceAmount;
    @Column(name ="invoiceNumber")
    private String invoiceNumber;
    @Column(name ="issueDate")
    private String issueDate;
    @Column(name ="PaymentStatus")
    private String PaymentStatus;

    public Invoice(){

    }

    public Invoice(long id, long invoiceAmount, String invoiceNumber, String issueDate, String paymentStatus) {
        this.id = id;
        this.invoiceAmount = invoiceAmount;
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        PaymentStatus = paymentStatus;
    }

    public void setId(long id){
        this.id = id;
    }
    public long getId(){
        return id;
    }

    public long getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(long invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

}


