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
public class Post extends BaseEntity {
    private String title;
    private String content;
    @ManyToOne
    private User author;
}
