package org.example.postproject.repository;

import jakarta.transaction.Transactional;
import org.example.postproject.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    /**
     * postlarni authorId(userId) boyicha deleted false larni olib keladi yani ochirimaganlarini
     *
     * @param pageable pageable qilib qaytarish uchu ozini ichaida page va size saqlaydi
     * @return
     */
    @Query(nativeQuery = true,
            value = "select * from post p where p.author_id=? and p.deleted=false"
    )
    Page<Post> findByAuthorId(UUID authorId, Pageable pageable);

    /**
     * osha user ga tegishli bolgan hamma postlarni deleted=true qilib qoyadi
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "update post set deleted=true where author_id=?")
    int deleteAllByUserId(UUID userId);

    /**
     * bitta post id va deleted false boyicha qidiradi va qaytaradi
     */
    Optional<Post> findByIdAndDeletedFalse(UUID uuid);
}
