package com.uep.wap.dto;

import com.uep.wap.model.TransferStatus;

import java.util.Date;
import java.util.List;

public class StockTransferDTO {

    private Long id;
    private Long fromWarehouseId;
    private Long toWarehouseId;
    private TransferStatus status;
    private Date requestedAt;
    private Date approvedAt;
    private List<StockTransferLineDTO> lines;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFromWarehouseId() { return fromWarehouseId; }
    public void setFromWarehouseId(Long fromWarehouseId) { this.fromWarehouseId = fromWarehouseId; }

    public Long getToWarehouseId() { return toWarehouseId; }
    public void setToWarehouseId(Long toWarehouseId) { this.toWarehouseId = toWarehouseId; }

    public TransferStatus getStatus() { return status; }
    public void setStatus(TransferStatus status) { this.status = status; }

    public Date getRequestedAt() { return requestedAt; }
    public void setRequestedAt(Date requestedAt) { this.requestedAt = requestedAt; }

    public Date getApprovedAt() { return approvedAt; }
    public void setApprovedAt(Date approvedAt) { this.approvedAt = approvedAt; }

    public List<StockTransferLineDTO> getLines() { return lines; }
    public void setLines(List<StockTransferLineDTO> lines) { this.lines = lines; }
}