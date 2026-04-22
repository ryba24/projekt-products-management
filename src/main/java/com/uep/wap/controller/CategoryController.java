package com.uep.wap.controller;

import com.uep.wap.dto.CategoryDTO;
import com.uep.wap.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoryDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public CategoryDTO create(@RequestBody CategoryDTO dto) {
        return service.create(dto);
    }
}