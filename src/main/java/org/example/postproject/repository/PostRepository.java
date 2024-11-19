package org.example.postproject.repository;

import jakarta.transaction.Transactional;
import org.example.postproject.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    @Query(nativeQuery = true,
            value ="select * from post p where p.author_id=?"
    )
    Page<Post> findByAuthorId(UUID authorId, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true,
    value = "update post set deleted=true where author_id=?")
    int deleteAllByUserId(UUID userId);
}
