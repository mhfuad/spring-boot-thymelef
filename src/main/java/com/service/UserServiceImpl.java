package com.service;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchUserList() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User updateUser(User user, Long userId) {
        User userDb = userRepository.findById(userId).get();
        if(Objects.nonNull(user.getName()) && !"".equalsIgnoreCase(user.getName())){
            userDb.setName(user.getName());
        }
        if(Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())){
            userDb.setEmail(user.getEmail());
        }
        if(Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())){
            userDb.setPassword(user.getPassword());
        }
        return userRepository.save(userDb);

    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
