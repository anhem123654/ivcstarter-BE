package com.ivcstarter.service;

import com.ivcstarter.entity.User;

public interface UserService {
    User registerUser(String username, String email, String password);
    User activateUser(Long userId, String key);
}
