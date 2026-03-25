package com.uep.wap.model;

import javax.persistence.*;

@Entity
@Table(name = "stock_transfer_lines")
public class StockTransferLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "transfer_id")
    private StockTransfer transfer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public StockTransferLine() {}

    public StockTransferLine(Integer quantity, StockTransfer transfer, Product product) {
        this.quantity = quantity;
        this.transfer = transfer;
        this.product = product;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public StockTransfer getTransfer() { return transfer; }
    public void setTransfer(StockTransfer transfer) { this.transfer = transfer; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}