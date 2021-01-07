package org.mavenworkforce.pojos.requests.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class SignupRequest {
    @NotBlank(message = "E-Mail address can not be blank.")
    @Size(max = 100)
    @Email(message = "E-Mail address should be a valid address.")
    private String email;

    @NotBlank(message = "Password can not be blank.")
    @Size(min = 6, max = 40, message = "Password length should be under the limit of 6-40 characters.")
    private String password;
}
