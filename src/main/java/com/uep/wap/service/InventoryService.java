package com.uep.wap.service;

import com.uep.wap.dto.InventoryItemDTO;
import com.uep.wap.model.InventoryItem;
import com.uep.wap.model.Product;
import com.uep.wap.model.Warehouse;
import com.uep.wap.repository.InventoryItemRepository;
import com.uep.wap.repository.ProductRepository;
import com.uep.wap.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<InventoryItemDTO> getAll() {
        List<InventoryItemDTO> result = new ArrayList<>();
        for (InventoryItem item : inventoryItemRepository.findAll()) {
            result.add(toDTO(item));
        }
        return result;
    }

    public InventoryItemDTO create(InventoryItemDTO dto) {
        Product product = productRepository.findById(Math.toIntExact(dto.getProductId()))
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + dto.getProductId()));
        Warehouse warehouse = warehouseRepository.findById(Math.toIntExact(dto.getWarehouseId()))
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + dto.getWarehouseId()));

        InventoryItem item = new InventoryItem();
        item.setQuantity(dto.getQuantity());
        item.setReorderThreshold(dto.getReorderThreshold());
        item.setProduct(product);
        item.setWarehouse(warehouse);

        InventoryItem saved = inventoryItemRepository.save(item);
        return toDTO(saved);
    }

    private InventoryItemDTO toDTO(InventoryItem item) {
        InventoryItemDTO dto = new InventoryItemDTO();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setReorderThreshold(item.getReorderThreshold());
        dto.setProductId(item.getProduct().getId());
        dto.setWarehouseId(item.getWarehouse().getId());
        return dto;
    }
}