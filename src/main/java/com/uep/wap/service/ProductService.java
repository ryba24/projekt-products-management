package com.uep.wap.service;

import com.uep.wap.dto.ProductDTO;
import com.uep.wap.model.Category;
import com.uep.wap.model.Product;
import com.uep.wap.repository.CategoryRepository;
import com.uep.wap.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAll() {
        List<ProductDTO> result = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            result.add(toDTO(product));
        }
        return result;
    }

    public ProductDTO getById(Long id) {
        Product product = productRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return toDTO(product);
    }

    public ProductDTO create(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setUnitPrice(dto.getUnitPrice());
        product.setActive(dto.getActive());
        product.setMinStockThreshold(dto.getMinStockThreshold());
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(Math.toIntExact(dto.getCategoryId()))
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + dto.getCategoryId()));
            product.setCategory(category);
        }
        Product saved = productRepository.save(product);
        return toDTO(saved);
    }

    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = productRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setUnitPrice(dto.getUnitPrice());
        product.setActive(dto.getActive());
        product.setMinStockThreshold(dto.getMinStockThreshold());
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(Math.toIntExact(dto.getCategoryId()))
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + dto.getCategoryId()));
            product.setCategory(category);
        }
        Product saved = productRepository.save(product);
        return toDTO(saved);
    }

    public void delete(Long id) {
        productRepository.deleteById(Math.toIntExact(id));
    }

    private ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setUnitPrice(product.getUnitPrice());
        dto.setActive(product.getActive());
        dto.setMinStockThreshold(product.getMinStockThreshold());
        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
        }
        return dto;
    }
}