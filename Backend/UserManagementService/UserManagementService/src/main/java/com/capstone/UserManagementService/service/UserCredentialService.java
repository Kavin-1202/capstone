package com.capstone.UserManagementService.service;

import com.capstone.UserManagementService.dto.AuthenticationResponse;
import com.capstone.UserManagementService.dto.UserCredentialDTO;
import com.capstone.UserManagementService.entity.Roles;
import com.capstone.UserManagementService.entity.UserCredential;
import com.capstone.UserManagementService.feign.ManagerClient;
import com.capstone.UserManagementService.feign.ManagerDTO;
import com.capstone.UserManagementService.repository.UserCredentialRepository;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserCredentialService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    ManagerClient managerClient;

    public UserCredentialDTO createUser(UserCredentialDTO userCredentialDTO) {

        try {
            UserCredential userCredential = new UserCredential();

            userCredential.setUsername(userCredentialDTO.getUsername());
            userCredential.setPassword(encoder.encode(userCredentialDTO.getPassword()));
            userCredential.setRole(userCredentialDTO.getRole());
            userCredential.setEmail(userCredentialDTO.getEmail());
            userCredential.setAccountid(userCredentialDTO.getAccountid());
            userCredential.setAccountname(userCredentialDTO.getAccountname());




            ManagerDTO managerDTO = new ManagerDTO();

            if (userCredential.getRole() == Roles.MANAGER) {

                managerDTO.setUsername(userCredentialDTO.getUsername());
                managerDTO.setEmail(userCredentialDTO.getEmail());
                managerDTO.setPassword(encoder.encode(userCredentialDTO.getPassword()));
                managerDTO.setRole(userCredentialDTO.getRole());
                managerDTO.setAccountid(userCredentialDTO.getAccountid());
                managerDTO.setAccountname(userCredentialDTO.getAccountname());
                managerClient.addManager(managerDTO);
            }
            userCredentialRepository.save(userCredential);
            return userCredentialDTO;
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Username or email already exists");
        }
    }

    public UserCredentialDTO getUserById(Long id) {
        UserCredential userCredential = userCredentialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(userCredential);
    }

    public List<UserCredentialDTO> getAllUsers() {
        return userCredentialRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserCredentialDTO updateUser(Long id, UserCredentialDTO userCredentialDTO) {
        UserCredential existingUser = userCredentialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(userCredentialDTO.getUsername());
        existingUser.setEmail(userCredentialDTO.getEmail());
        existingUser.setPassword(userCredentialDTO.getPassword());
        existingUser.setRole(userCredentialDTO.getRole());

        UserCredential updatedUser = userCredentialRepository.save(existingUser);
        return mapToDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userCredentialRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userCredentialRepository.deleteById(id);
    }

    private UserCredentialDTO mapToDTO(UserCredential userCredential) {
        return new UserCredentialDTO(
                userCredential.getUsername(),
                userCredential.getEmail(),
                userCredential.getPassword(),
                userCredential.getRole(),
                userCredential.getAccountid(),
                userCredential.getAccountname()
                        );
    }

    public UserCredentialDTO getUserByName(String name) {
        UserCredential userCredential = userCredentialRepository.findByUsername(name).orElse(null);
        if (userCredential != null)
            return mapToDTO(userCredential);
        return null;
    }

    public Optional<UserCredential> authenticate(String username, String password) {
        Optional<UserCredential> user = userCredentialRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    public AuthenticationResponse verify(UserCredential user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(user.getUsername());
            Optional<UserCredential> existingUser = userCredentialRepository.findByUsername(user.getUsername());
            if (existingUser.isPresent() && existingUser.get().getRole() ==Roles.MANAGER) {
                UserCredential existingManager = userCredentialRepository.findByUsername(existingUser.get().getUsername()).get();
                return new AuthenticationResponse(token, existingManager.getUsername(), existingUser.get().getRole());
            } else if (existingUser.isPresent() && existingUser.get().getRole() == Roles.EMPLOYEE) {
                UserCredential existingEmployee = userCredentialRepository.findByUsername(existingUser.get().getUsername()).get();
                return new AuthenticationResponse(token, existingEmployee.getUsername(), existingUser.get().getRole());
            }else if (existingUser.isPresent() && existingUser.get().getRole() == Roles.ADMIN) {
                UserCredential admin = userCredentialRepository.findByUsername(existingUser.get().getUsername()).get();
                return new AuthenticationResponse(token, admin.getUsername(), existingUser.get().getRole());
            }else {
                throw new RuntimeException("User not found.");
            }

        }
        return new AuthenticationResponse("Failed", null, null);

    }
}
