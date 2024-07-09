package com.cadence.controller;

import com.cadence.model.User;
import com.cadence.response.MessageResponse;
import com.cadence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUser(@RequestHeader("Authorization") String jwt) throws Exception {
        List<User> users = userService.findAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<MessageResponse> deleteUserById(@PathVariable long userId, @RequestHeader("Authorization") String jwt) throws Exception {
        userService.deleteUser(userId);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("User with id: " + userId +  " was deleted successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
