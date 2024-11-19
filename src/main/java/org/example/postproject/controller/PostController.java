package org.example.postproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/post")
@RequiredArgsConstructor
public class PostController {
    @GetMapping("hello")
    private String  hello() {
        return "Hello World";
    }
}
