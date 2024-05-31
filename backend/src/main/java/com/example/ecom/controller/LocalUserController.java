package com.example.ecom.controller;

import com.example.ecom.model.LocalUser;
import com.example.ecom.service.LocalUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class LocalUserController {
    private final LocalUserService localUserService;

    public LocalUserController(LocalUserService localUserService) {
        this.localUserService = localUserService;
    }

    @PostMapping("/saveuser")
    public LocalUser saveUser(@RequestBody LocalUser user) {
        return localUserService.saveUser(user);
    }

    @GetMapping("/getallusers")
    public List<LocalUser> getAllUsers() {
        return localUserService.getAllUsers();
    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<LocalUser> getUserById(@PathVariable Long id) {
        Optional<LocalUser> user = localUserService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<LocalUser> updateUser(@PathVariable Long id, @RequestBody LocalUser updatedUser) {
        LocalUser result = localUserService.updateUser(id, updatedUser);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        localUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

