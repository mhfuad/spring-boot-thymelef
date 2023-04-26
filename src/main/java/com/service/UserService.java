package com.service;

import com.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> fetchUserList();

    User updateUser(User user, Long userId);

    void deleteUserById(Long userId);
}
