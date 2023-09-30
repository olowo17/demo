package com.michael.demo.dto;

import java.time.LocalDate;


public record UserDto (String fullName,
                       String email,
                       LocalDate dateOfBirth,
                       Long addressId,
                       String password )
{
}
