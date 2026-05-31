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

    public InventoryItemDTO getById(Long id) {
        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory item not found with id: " + id));
        return toDTO(item);
    }

    public InventoryItemDTO create(InventoryItemDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + dto.getProductId()));
        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + dto.getWarehouseId()));

        InventoryItem item = new InventoryItem();
        item.setQuantity(dto.getQuantity());
        item.setReorderThreshold(dto.getReorderThreshold());
        item.setProduct(product);
        item.setWarehouse(warehouse);
        return toDTO(inventoryItemRepository.save(item));
    }

    public InventoryItemDTO update(Long id, InventoryItemDTO dto) {
        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory item not found with id: " + id));
        if (dto.getQuantity()         != null) item.setQuantity(dto.getQuantity());
        if (dto.getReorderThreshold() != null) item.setReorderThreshold(dto.getReorderThreshold());
        if (dto.getProductId() != null) {
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + dto.getProductId()));
            item.setProduct(product);
        }
        if (dto.getWarehouseId() != null) {
            Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                    .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + dto.getWarehouseId()));
            item.setWarehouse(warehouse);
        }
        return toDTO(inventoryItemRepository.save(item));
    }

    public void delete(Long id) {
        if (!inventoryItemRepository.existsById(id)) {
            throw new RuntimeException("Inventory item not found with id: " + id);
        }
        inventoryItemRepository.deleteById(id);
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
