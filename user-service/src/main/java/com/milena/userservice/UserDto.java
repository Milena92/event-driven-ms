package com.milena.userservice;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDto {

    @NotBlank(message = "First name can not be blank.")
    private String firstName;

    @NotBlank(message = "Last name can not be blank.")
    private String lastName;

    @Email
    @NotBlank(message = "Email can not be blank")
    private String email;
}
