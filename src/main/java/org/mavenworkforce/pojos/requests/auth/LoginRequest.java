package org.mavenworkforce.pojos.requests.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "E-Mail address can not be blank.")
    @Size(max = 100)
    @Email(message = "E-Mail address should be a valid address.")
    @NotEmpty(message = "Password can not be empty.")
    @NotNull(message = "Password can not be null.")
    private String email;

    @NotBlank(message = "Password can not be blank.")
    @NotEmpty(message = "Password can not be empty.")
    @NotNull(message = "Password can not be null.")
    private String password;
}
