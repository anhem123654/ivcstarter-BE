package com.ivcstarter.service.impl;

import com.ivcstarter.entity.*;
import com.ivcstarter.repository.*;
import com.ivcstarter.service.ActivationKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivationKeyServiceImpl implements ActivationKeyService {

    private final ActivationKeyRepository activationKeyRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    // Tạo key kích hoạt mới
    @Override
    public ActivationKey createActivationKey(User creator, Role targetRole, Department department, String jobTitle) {
        String key = generateKey(6);

        ActivationKey activationKey = ActivationKey.builder()
                .encodedKey(key)
                .creator(creator)
                .targetRole(targetRole)
                .targetDepartment(department)
                .targetJobTitle(jobTitle)
                .isUsed(false)
                .createdAt(LocalDateTime.now())
                .expiryTime(LocalDateTime.now().plusHours(24))
                .build();

        return activationKeyRepository.save(activationKey);
    }

    private String generateKey(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    @Override
    public Optional<ActivationKey> validateKey(String key) {
        Optional<ActivationKey> activationKeyOpt = activationKeyRepository.findByEncodedKey(key);
        if (activationKeyOpt.isEmpty()) return Optional.empty();

        ActivationKey activationKey = activationKeyOpt.get();
        if (activationKey.isUsed() || activationKey.getExpiryTime().isBefore(LocalDateTime.now())) {
            return Optional.empty();
        }

        return activationKeyOpt;
    }

    @Override
    public void markAsUsed(ActivationKey key) {
        key.setUsed(true);
        activationKeyRepository.save(key);
    }

    // Xóa key hết hạn mỗi 1 giờ
    @Scheduled(fixedRate = 3600000)
    public void deleteExpiredKeys() {
        activationKeyRepository.deleteByExpiryTimeBefore(LocalDateTime.now());
    }
}
