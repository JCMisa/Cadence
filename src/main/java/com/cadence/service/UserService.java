package com.cadence.service;

import com.cadence.model.User;

import java.util.List;


public interface UserService
{
    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;

    public List<User> findAllUser() throws Exception;
    public User findUserById(Long userId) throws Exception;
    public void deleteUser(Long userId) throws Exception;
}
