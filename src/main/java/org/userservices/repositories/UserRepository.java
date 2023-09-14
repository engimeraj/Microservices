package org.userservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.userservices.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
}
