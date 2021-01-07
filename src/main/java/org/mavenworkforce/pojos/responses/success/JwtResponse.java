package org.mavenworkforce.pojos.responses.success;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String email;

    public JwtResponse(String token, String email) {
        this.email = email;
        this.token = token;
    }
}