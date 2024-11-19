package org.example.postproject.mappers;

import lombok.RequiredArgsConstructor;
import org.example.postproject.dtos.UserGetDto;
import org.example.postproject.entities.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
/**
 * userga tegishli ozgartirishlar
 */
public class UserMapper {
    public UserGetDto toUserGetDto(User user) {
        UserGetDto userGetDto = new UserGetDto();
        userGetDto.setId(user.getId());
        userGetDto.setUsername(user.getUsername());
        return userGetDto;
    }
}
