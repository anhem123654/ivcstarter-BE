package com.ivcstarter.service.impl;

import com.ivcstarter.entity.*;
import com.ivcstarter.repository.*;
import com.ivcstarter.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    // private final RoleRepository roleRepository;
    // private final DepartmentRepository departmentRepository;
    private final ActivationKeyService activationKeyService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(String username, String email, String password) {
        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .isActive(false)
                .createdAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    @Override
    public User activateUser(Long userId, String key) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ActivationKey activationKey = activationKeyService.validateKey(key)
                .orElseThrow(() -> new RuntimeException("Invalid or expired activation key"));

        user.setAssignedRole(activationKey.getTargetRole());
        user.setDepartment(activationKey.getTargetDepartment());
        user.setJobTitle(activationKey.getTargetJobTitle());
        user.setManager(activationKey.getCreator());
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());

        activationKeyService.markAsUsed(activationKey);

        return userRepository.save(user);
    }
}
