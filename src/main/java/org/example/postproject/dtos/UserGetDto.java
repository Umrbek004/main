package org.example.postproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * userlarni hamma malumotioni ochiqlamay get qilish uchun ishlatilai
 */
public class UserGetDto {
    private UUID id;
    private String username;
}
