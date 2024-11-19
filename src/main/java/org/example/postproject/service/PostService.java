package org.example.postproject.service;

import lombok.RequiredArgsConstructor;
import org.example.postproject.dtos.CreatePostDto;
import org.example.postproject.dtos.PostGetDto;
import org.example.postproject.entities.Post;
import org.example.postproject.entities.User;
import org.example.postproject.mappers.PostMapper;
import org.example.postproject.repository.PostRepository;
import org.example.postproject.response.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public ResponseData<?> createPost(CreatePostDto createPostDto) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = new Post();
        post.setTitle(createPostDto.getTitle());
        post.setContent(createPostDto.getContent());
        post.setAuthor(principal);
        postRepository.save(post);
        return ResponseData.successResponse("Post created successfully");
    }

    public ResponseData<?> getAllOwnPosts(int page, int size,UUID userId) {
        if (userId == null) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userId = user.getId();
        }
        Page<Post> byAuthorId = postRepository.findByAuthorId(userId, PageRequest.of(page, size));
        if (!byAuthorId.hasContent()) {
            return ResponseData.successResponse("No posts found");
        }
        List<PostGetDto> list = byAuthorId.get().map(post -> postMapper.toPostGetDto(post)).toList();
        Map<String, Object> result = new HashMap<>();
        result.put("data", list);
        result.put("total", byAuthorId.getTotalElements());
        result.put("TotalPages", byAuthorId.getTotalPages());
        return ResponseData.successResponse(result);
    }

    public ResponseData<?> getOneById(UUID id) {
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isEmpty()) {
            return new ResponseData<>("post did not find", false);
        }
        return ResponseData.successResponse(postMapper.toPostGetDto(byId.get()));
    }

    public ResponseData<?> deleteOneById(UUID id) {
        Optional<Post> byId = postRepository.findById(id);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (byId.isEmpty()) {
            return new ResponseData<>("post did not find", false);
        }
        Post post = byId.get();
        if (post.getAuthor().getId() != principal.getId()) {
            return new ResponseData<>("This post does not belong to this user", false);
        }
        post.setDeleted(true);
        postRepository.save(post);
        return null;
    }
    @Modifying
    public ResponseData<?> deleteAll() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int i = postRepository.deleteAllByUserId(principal.getId());
        if (i > 0) {
            return new ResponseData<>("Posts deleted successfully", false);
        }
        return ResponseData.successResponse("Posts already deleted");
    }

    public ResponseData<?> updateOneById(UUID id,PostGetDto updatedPost) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isEmpty()) {
            return new ResponseData<>("post did not find", false);
        }
        Post post = byId.get();
        if (post.getAuthor().getId() != principal.getId()) {
            return new ResponseData<>("This post does not belong to this user", false);
        }
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        postRepository.save(post);
        return ResponseData.successResponse("Post updated successfully");
    }
}
