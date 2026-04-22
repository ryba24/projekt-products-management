package com.uep.wap.service;

import com.uep.wap.dto.WarehouseDTO;
import com.uep.wap.model.Warehouse;
import com.uep.wap.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<WarehouseDTO> getAll() {
        List<WarehouseDTO> result = new ArrayList<>();
        for (Warehouse warehouse : warehouseRepository.findAll()) {
            result.add(toDTO(warehouse));
        }
        return result;
    }

    public WarehouseDTO create(WarehouseDTO dto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());
        warehouse.setCapacity(dto.getCapacity());
        Warehouse saved = warehouseRepository.save(warehouse);
        return toDTO(saved);
    }

    private WarehouseDTO toDTO(Warehouse warehouse) {
        WarehouseDTO dto = new WarehouseDTO();
        dto.setId(warehouse.getId());
        dto.setName(warehouse.getName());
        dto.setLocation(warehouse.getLocation());
        dto.setCapacity(warehouse.getCapacity());
        return dto;
    }
}