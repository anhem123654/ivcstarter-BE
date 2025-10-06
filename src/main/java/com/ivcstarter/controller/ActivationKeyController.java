package com.ivcstarter.controller;

import com.ivcstarter.entity.*;
import com.ivcstarter.repository.*;
import com.ivcstarter.service.ActivationKeyService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activation")
@RequiredArgsConstructor
public class ActivationKeyController {

    private final ActivationKeyService activationKeyService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createActivationKey(@RequestBody CreateKeyRequest request) {
        User creator = userRepository.findById(request.getCreatorId())
                .orElseThrow(() -> new RuntimeException("Creator not found"));
        Role role = roleRepository.findById(request.getTargetRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        Department department = departmentRepository.findById(request.getTargetDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        ActivationKey key = activationKeyService.createActivationKey(
                creator, role, department, request.getTargetJobTitle()
        );

        return ResponseEntity.ok("Activation Key: " + key.getEncodedKey());
    }

    @Data
    static class CreateKeyRequest {
        private Long creatorId;
        private Long targetRoleId;
        private Long targetDepartmentId;
        private String targetJobTitle;
    }
}
