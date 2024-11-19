package org.example.postproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Post ni create qilish uchun ishlatiladigan Dto
 */
public class CreatePostDto {
    private String title;
    private String content;
}
