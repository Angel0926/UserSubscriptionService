package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.AppUser;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<AppUser> create(@RequestBody AppUser user) {
        return ResponseEntity.ok(service.create(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> update(@PathVariable("id") Long id, @RequestBody AppUser user) {
        return ResponseEntity.ok(service.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
