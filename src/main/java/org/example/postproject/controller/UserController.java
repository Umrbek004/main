package org.example.postproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.postproject.response.ResponseData;
import org.example.postproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("get-all-users")
    public ResponseData<?> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return this.userService.getAllUsers(page,size);
    }

    @PostMapping("search")
    public ResponseData<?> search(@RequestParam String word) {
        return this.userService.search(word);
    }
}
