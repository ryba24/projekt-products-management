package com.uep.wap.service;

import com.uep.wap.dto.StockTransferDTO;
import com.uep.wap.dto.StockTransferLineDTO;
import com.uep.wap.model.Product;
import com.uep.wap.model.StockTransfer;
import com.uep.wap.model.StockTransferLine;
import com.uep.wap.model.TransferStatus;
import com.uep.wap.model.Warehouse;
import com.uep.wap.repository.ProductRepository;
import com.uep.wap.repository.StockTransferLineRepository;
import com.uep.wap.repository.StockTransferRepository;
import com.uep.wap.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StockTransferService {

    @Autowired
    private StockTransferRepository stockTransferRepository;

    @Autowired
    private StockTransferLineRepository stockTransferLineRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<StockTransferDTO> getAll() {
        List<StockTransferDTO> result = new ArrayList<>();
        for (StockTransfer transfer : stockTransferRepository.findAll()) {
            result.add(toDTO(transfer));
        }
        return result;
    }

    public StockTransferDTO getById(Long id) {
        StockTransfer transfer = stockTransferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transfer not found with id: " + id));
        return toDTO(transfer);
    }

    public StockTransferDTO create(StockTransferDTO dto) {
        Warehouse fromWarehouse = warehouseRepository.findById(dto.getFromWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + dto.getFromWarehouseId()));
        Warehouse toWarehouse = warehouseRepository.findById(dto.getToWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + dto.getToWarehouseId()));

        StockTransfer transfer = new StockTransfer();
        transfer.setFromWarehouse(fromWarehouse);
        transfer.setToWarehouse(toWarehouse);
        transfer.setStatus(dto.getStatus() != null ? dto.getStatus() : TransferStatus.REQUESTED);
        transfer.setRequestedAt(new Date());

        StockTransfer saved = stockTransferRepository.save(transfer);

        if (dto.getLines() != null) {
            for (StockTransferLineDTO lineDTO : dto.getLines()) {
                Product product = productRepository.findById(lineDTO.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found with id: " + lineDTO.getProductId()));
                StockTransferLine line = new StockTransferLine();
                line.setQuantity(lineDTO.getQuantity());
                line.setTransfer(saved);
                line.setProduct(product);
                stockTransferLineRepository.save(line);
            }
        }

        return toDTO(stockTransferRepository.findById(saved.getId()).orElse(saved));
    }

    public StockTransferDTO updateStatus(Long id, TransferStatus newStatus) {
        StockTransfer transfer = stockTransferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transfer not found with id: " + id));
        transfer.setStatus(newStatus);
        if (newStatus == TransferStatus.APPROVED) {
            transfer.setApprovedAt(new Date());
        }
        return toDTO(stockTransferRepository.save(transfer));
    }

    public void delete(Long id) {
        if (!stockTransferRepository.existsById(id)) {
            throw new RuntimeException("Transfer not found with id: " + id);
        }
        stockTransferRepository.deleteById(id);
    }

    private StockTransferDTO toDTO(StockTransfer transfer) {
        StockTransferDTO dto = new StockTransferDTO();
        dto.setId(transfer.getId());
        dto.setFromWarehouseId(transfer.getFromWarehouse().getId());
        dto.setToWarehouseId(transfer.getToWarehouse().getId());
        dto.setStatus(transfer.getStatus());
        dto.setRequestedAt(transfer.getRequestedAt());
        dto.setApprovedAt(transfer.getApprovedAt());

        List<StockTransferLineDTO> lineDTOs = new ArrayList<>();
        if (transfer.getLines() != null) {
            for (StockTransferLine line : transfer.getLines()) {
                StockTransferLineDTO lineDTO = new StockTransferLineDTO();
                lineDTO.setId(line.getId());
                lineDTO.setQuantity(line.getQuantity());
                lineDTO.setTransferId(transfer.getId());
                lineDTO.setProductId(line.getProduct().getId());
                lineDTOs.add(lineDTO);
            }
        }
        dto.setLines(lineDTOs);
        return dto;
    }
}