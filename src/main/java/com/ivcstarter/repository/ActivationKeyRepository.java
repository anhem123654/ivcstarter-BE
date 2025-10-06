package com.ivcstarter.repository;

import com.ivcstarter.entity.ActivationKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.time.LocalDateTime;

public interface ActivationKeyRepository extends JpaRepository<ActivationKey, Long> {
    Optional<ActivationKey> findByEncodedKey(String activationKey);
    Optional<ActivationKey> findByEmployeeCode(String employeeCode);
    void deleteByExpiryTimeBefore(LocalDateTime now);
}
