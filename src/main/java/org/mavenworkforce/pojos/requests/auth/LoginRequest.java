package org.mavenworkforce.pojos.requests.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "E-Mail address can not be blank.")
    @Size(max = 100)
    @Email(message = "E-Mail address should be a valid address.")
    private String email;

    @NotBlank(message = "Password can not be blank.")
    private String password;
}
