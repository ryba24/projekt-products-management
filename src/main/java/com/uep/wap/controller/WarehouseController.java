package com.uep.wap.controller;

import com.uep.wap.dto.WarehouseDTO;
import com.uep.wap.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    @GetMapping
    public List<WarehouseDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public WarehouseDTO create(@RequestBody WarehouseDTO dto) {
        return service.create(dto);
    }
}