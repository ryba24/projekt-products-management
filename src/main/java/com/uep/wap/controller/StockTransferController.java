package com.uep.wap.controller;

import com.uep.wap.dto.StockTransferDTO;
import com.uep.wap.service.StockTransferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class StockTransferController {

    private final StockTransferService service;

    public StockTransferController(StockTransferService service) {
        this.service = service;
    }

    @GetMapping
    public List<StockTransferDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public StockTransferDTO create(@RequestBody StockTransferDTO dto) {
        return service.create(dto);
    }
}