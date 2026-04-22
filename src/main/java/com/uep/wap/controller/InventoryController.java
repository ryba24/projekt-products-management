package com.uep.wap.controller;

import com.uep.wap.dto.InventoryItemDTO;
import com.uep.wap.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<InventoryItemDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public InventoryItemDTO create(@RequestBody InventoryItemDTO dto) {
        return service.create(dto);
    }
}