package com.uep.wap.controller;

import com.uep.wap.dto.StockTransferDTO;
import com.uep.wap.model.TransferStatus;
import com.uep.wap.service.StockTransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transfers")
public class StockTransferController {

    private final StockTransferService service;

    public StockTransferController(StockTransferService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StockTransferDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockTransferDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<StockTransferDTO> create(@RequestBody StockTransferDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<StockTransferDTO> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        TransferStatus newStatus = TransferStatus.valueOf(body.get("status"));
        return ResponseEntity.ok(service.updateStatus(id, newStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}