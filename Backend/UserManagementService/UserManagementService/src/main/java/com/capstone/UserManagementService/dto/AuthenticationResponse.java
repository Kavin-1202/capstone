package com.capstone.UserManagementService.dto;


import com.capstone.UserManagementService.entity.Roles;
import com.capstone.UserManagementService.entity.UserCredential;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private String username;
    @Enumerated(value = EnumType.STRING)
    private Roles roles;
}

