package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.User;

public interface UserService {
    void createUser(User user);
    User getUserById(long id);
}
