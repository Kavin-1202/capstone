package com.capstone.UserManagementService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;
    private String password;
    private Roles role;
    private  Long accountid;
    private String accountname;

    public enum Roles {
        MANAGER,
        ADMIN,
        USER
    }
}
