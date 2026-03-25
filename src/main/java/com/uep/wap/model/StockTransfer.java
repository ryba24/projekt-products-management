package com.uep.wap.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "stock_transfers")
public class StockTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_warehouse_id")
    private Warehouse fromWarehouse;

    @ManyToOne
    @JoinColumn(name = "to_warehouse_id")
    private Warehouse toWarehouse;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransferStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "requested_at")
    private Date requestedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "approved_at")
    private Date approvedAt;

    @OneToMany(mappedBy = "transfer", cascade = CascadeType.ALL)
    private List<StockTransferLine> lines;

    public StockTransfer() {}

    public StockTransfer(Warehouse fromWarehouse, Warehouse toWarehouse, TransferStatus status, Date requestedAt) {
        this.fromWarehouse = fromWarehouse;
        this.toWarehouse = toWarehouse;
        this.status = status;
        this.requestedAt = requestedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Warehouse getFromWarehouse() { return fromWarehouse; }
    public void setFromWarehouse(Warehouse fromWarehouse) { this.fromWarehouse = fromWarehouse; }

    public Warehouse getToWarehouse() { return toWarehouse; }
    public void setToWarehouse(Warehouse toWarehouse) { this.toWarehouse = toWarehouse; }

    public TransferStatus getStatus() { return status; }
    public void setStatus(TransferStatus status) { this.status = status; }

    public Date getRequestedAt() { return requestedAt; }
    public void setRequestedAt(Date requestedAt) { this.requestedAt = requestedAt; }

    public Date getApprovedAt() { return approvedAt; }
    public void setApprovedAt(Date approvedAt) { this.approvedAt = approvedAt; }

    public List<StockTransferLine> getLines() { return lines; }
    public void setLines(List<StockTransferLine> lines) { this.lines = lines; }
}