package org.example.postproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.postproject.dtos.CreatePostDto;
import org.example.postproject.dtos.PostGetDto;
import org.example.postproject.response.ResponseData;
import org.example.postproject.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("hello")
    private String hello() {
        return "Hello World";
    }

    @PostMapping("create")
    public ResponseData<?> createPost(@RequestBody CreatePostDto createPostDto) {
        return this.postService.createPost(createPostDto);
    }

    @GetMapping("get-all-own-posts")
    public ResponseData<?> getAllOwnPosts(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(required = false) UUID userId) {
        return this.postService.getAllOwnPosts(page, size,userId );
    }

    @GetMapping("get-one-by-id/{id}")
    public ResponseData<?> getOnePostById(@PathVariable UUID id) {
        return this.postService.getOneById(id);
    }

    @DeleteMapping("delete-one-by-id/{id}")
    public ResponseData<?> deleteOnePostById(@PathVariable UUID id) {
        return this.postService.deleteOneById(id);
    }

    @DeleteMapping("delete-all-own-posts")
    public ResponseData<?> deleteAllOwnPosts() {
        return this.postService.deleteAll();
    }

    @PutMapping("update-one-by-id/{id}")
    public ResponseData<?> update(@PathVariable UUID id, @RequestBody PostGetDto postGetDto) {
        return this.postService.updateOneById(id, postGetDto);
    }

}
