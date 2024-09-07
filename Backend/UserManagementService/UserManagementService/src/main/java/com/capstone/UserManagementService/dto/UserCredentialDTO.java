package com.capstone.UserManagementService.dto;


import com.capstone.UserManagementService.entity.Roles;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentialDTO {
    private String username;
    private String email;
    private String password;
    private Roles role;
    private  Long accountid;
    private String accountname;
}

