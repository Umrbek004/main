package org.example.postproject.mappers;

import lombok.RequiredArgsConstructor;
import org.example.postproject.dtos.PostGetDto;
import org.example.postproject.entities.Post;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper {
    public PostGetDto toPostGetDto (Post post) {
        PostGetDto postGetDto = new PostGetDto();
        postGetDto.setId(post.getId());
        postGetDto.setTitle(post.getTitle());
        postGetDto.setContent(post.getContent());
        return postGetDto;
    }
}
