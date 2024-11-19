package org.example.postproject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.postproject.entities.base.BaseEntity;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity

/**
 * Post table yaratib berish uchun kerak bolgan class
 */
public class Post extends BaseEntity {
    private String title;
    private String content;
    @ManyToOne // 1 ta user ko'p post yarata olishi uchun shu annotatsiyadan foydalaniladi
    private User author;
}
