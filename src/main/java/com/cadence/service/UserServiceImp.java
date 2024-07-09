package com.cadence.service;

import com.cadence.config.JwtProvider;
import com.cadence.model.User;
import com.cadence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = findUserByEmail(email);
        if(user == null) {
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if(user == null)
        {
            throw new Exception("User not found");
        }

        return user;
    }

    @Override
    public List<User> findAllUser() throws Exception {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) {
            throw new Exception("No user found");
        }
        return users;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()) {
            throw new Exception("User not found");
        }

        return optionalUser.get();
    }

    @Override
    public void deleteUser(Long userId) throws Exception {
        User existingUser = findUserById(userId);
        userRepository.delete(existingUser);
    }
}
