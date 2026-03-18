package com.uep.wap.model;

import javax.persistence.*;

@Entity
@Table(name="invoice")
public class Invoice {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//
    private long id;
    @Column(name ="invoiceNumber")
    private String invoiceNumber;
    @Column(name ="points")
    private Integer points;
    @Column(name ="name")

    public void setId(long id){
        this.id = id;
    }
    public long getId(){
        return id;
    }
    public Invoice(){

    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Invoice(String invoiceNumber, Integer points){

    }
}


