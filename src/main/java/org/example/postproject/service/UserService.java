package org.example.postproject.service;

import lombok.RequiredArgsConstructor;
import org.example.postproject.dtos.UserGetDto;
import org.example.postproject.entities.User;
import org.example.postproject.mappers.UserMapper;
import org.example.postproject.repository.UserRepository;
import org.example.postproject.response.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseData<?> getAllUsers(int page, int size) {
        Page<User> all = userRepository.findAll(PageRequest.of(page, size));
        if (!all.hasContent()) {
            return ResponseData.successResponse("Users not found");
        }
        List<UserGetDto> list = all.get().map(a -> userMapper.toUserGetDto(a)).toList();
        Map<String, Object> result = new HashMap<>();
        result.put("data", list);
        result.put("total", all.getTotalElements());
        result.put("TotalPages", all.getTotalPages());
        return ResponseData.successResponse(result);
    }

    public ResponseData search(String word) {
        List<User> byUsernameContaining = userRepository.findByUsernameContaining(word);
        if (byUsernameContaining.isEmpty()) {
            return ResponseData.successResponse("User not found");
        }
        List<UserGetDto> userGetDtos=new LinkedList<>();
        for (User user : byUsernameContaining) {
            userGetDtos.add(userMapper.toUserGetDto(user));
        }
        return ResponseData.successResponse(userGetDtos);
    }
}
