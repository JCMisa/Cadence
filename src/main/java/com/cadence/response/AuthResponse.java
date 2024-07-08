package com.cadence.response;

import com.cadence.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse
{
    private String jwt;
    private String message;
    private USER_ROLE role;
}
