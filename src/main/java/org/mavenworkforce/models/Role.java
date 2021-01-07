package org.mavenworkforce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "roles")
public class Role extends Auditable{
    @Id
    private String id;

    private EnumRole name;
}
