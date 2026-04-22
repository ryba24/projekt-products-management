package com.uep.wap.controller;

import com.uep.wap.dto.UserDTO;
import com.uep.wap.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO dto) {
        return service.create(dto);
    }
}