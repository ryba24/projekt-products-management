package com.uep.wap.model;

import javax.persistence.*;

@Entity
@Table(name="order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private long id;

    @Column(name ="order_id")
    private Long orderId;

    @Column(name ="product_id")
    private Long productId;

    @Column(name ="quantity")
    private Integer quantity;

    @Column(name ="unit_price")
    private Double unitPrice;

    @Column(name ="total")
    private Double total;

    public OrderLine() {}

    public OrderLine(Long orderId, Long productId, Integer quantity, Double unitPrice, Double total) {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}