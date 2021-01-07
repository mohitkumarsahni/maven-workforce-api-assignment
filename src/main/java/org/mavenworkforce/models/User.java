package org.mavenworkforce.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Document(collection = "users")
public class User extends Auditable{
    @Id
    private String id;

    private String name;

    @Email(message = "E-Mail address should be a valid address.")
    @NotBlank(message = "E-Mail address can not be blank.")
    @Indexed(unique=true)
    private String email;

    @NotBlank(message = "Password can not be blank.")
    private String password;

    @NotBlank(message = "Integer can not be blank. Setting it yo 0.")
    private Long integer;

    @DBRef
    private Set<Role> roles;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
