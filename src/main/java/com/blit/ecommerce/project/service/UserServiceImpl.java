package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.User;
import com.blit.ecommerce.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }
    @Override
    public User getUserById(long id) {
        return userRepository.findById((int) id).orElse(null);
    }
}
