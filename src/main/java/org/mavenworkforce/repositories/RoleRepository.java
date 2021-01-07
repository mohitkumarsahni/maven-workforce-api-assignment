package org.mavenworkforce.repositories;


import org.mavenworkforce.models.EnumRole;
import org.mavenworkforce.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(EnumRole name);
}
