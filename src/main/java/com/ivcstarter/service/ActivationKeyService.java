package com.ivcstarter.service;

import com.ivcstarter.entity.*;

import java.util.Optional;

public interface ActivationKeyService {
    ActivationKey createActivationKey(User creator, Role targetRole, Department department, String jobTitle);
    Optional<ActivationKey> validateKey(String key);
    void markAsUsed(ActivationKey key);
    void deleteExpiredKeys();
}
