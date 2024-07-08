package com.cadence.service;

import com.cadence.model.User;


public interface UserService
{
    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;
}
