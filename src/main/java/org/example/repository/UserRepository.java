package org.example.repository;

import org.example.model.AppUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    @EntityGraph(attributePaths = "subscriptions")
    Optional<AppUser> findById(Long id);

}
