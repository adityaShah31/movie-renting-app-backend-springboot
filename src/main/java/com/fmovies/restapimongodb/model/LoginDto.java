package com.fmovies.restapimongodb.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDto {

    @NotBlank(message = "Email address cannot be empty!")
    @Email(message = "Provide a valid email address FOOL!")
    private String email;

    @NotBlank(message = "Password cannot be empty!")
    @Size(min = 8, max = 20, message = "Password has to be between 8 and 20 characters long!")
    private String password;

    public LoginDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
