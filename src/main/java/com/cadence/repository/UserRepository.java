package com.cadence.repository;

import com.cadence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    // find the user by email
    public User findByEmail (String username);
}
