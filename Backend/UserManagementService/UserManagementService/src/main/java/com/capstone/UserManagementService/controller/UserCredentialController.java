package com.capstone.UserManagementService.controller;


import com.capstone.UserManagementService.dto.AuthenticationResponse;
import com.capstone.UserManagementService.dto.LoginDTO;
import com.capstone.UserManagementService.dto.UserCredentialDTO;
import com.capstone.UserManagementService.entity.UserCredential;
import com.capstone.UserManagementService.service.UserCredentialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserCredentialController {

    @Autowired
    private UserCredentialService userCredentialService;

    @PostMapping("/signup")
    public ResponseEntity<UserCredentialDTO> createUser(@RequestBody UserCredentialDTO userCredentialDTO) {
        UserCredentialDTO createdUser = userCredentialService.createUser(userCredentialDTO);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserCredentialDTO> getUserById(@PathVariable Long id) {
        UserCredentialDTO user = userCredentialService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/byName/{name}")
    public ResponseEntity<UserCredentialDTO> getUserByName(@PathVariable String name) {
        UserCredentialDTO user = userCredentialService.getUserByName(name);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserCredentialDTO>> getAllUsers() {
        List<UserCredentialDTO> users = userCredentialService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserCredentialDTO> updateUser(@PathVariable Long id, @RequestBody UserCredentialDTO userCredentialDTO) {
        UserCredentialDTO updatedUser = userCredentialService.updateUser(id, userCredentialDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userCredentialService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserCredential user) {

        try {
            return ResponseEntity.ok(userCredentialService.verify(user));
        }

        catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
