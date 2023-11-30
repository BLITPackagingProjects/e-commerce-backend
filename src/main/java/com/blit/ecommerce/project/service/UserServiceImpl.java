package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.entities.PasswordResetToken;
import com.blit.ecommerce.project.entities.User;
import com.blit.ecommerce.project.repository.PasswordResetTokenRepository;
import com.blit.ecommerce.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }
    @Override
    public User getUserById(long id) {
        return userRepository.findById((int) id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByUsername(email).orElseThrow();
    }

    @Override
    public void createPasswordResetToken(User user, String tokenStr) {
        PasswordResetToken token = new PasswordResetToken(tokenStr, user);
        passwordResetTokenRepository.save(token);
    }
    @Override
    public User findUserByResetToken(String resetToken){
        return userRepository.findByResetToken(resetToken);
    }
}
