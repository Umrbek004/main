package org.example.postproject.repository;

import org.example.postproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * username boyicha userlarni qidirib keladi
     */
    Optional<User> findByUsername(String username);

    /**
     * search qilinayotgan worni oz ichiga olgan hamma usernamlarni topib keladi
     */
    List<User> findByUsernameContaining(String word);
}
