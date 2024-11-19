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

    /**
     * post yaratish yo'liga uzatadi
     *
     * @param createPostDto -- faqat title va content kirib keladi (string korinishida)
     */
    @PostMapping("create")
    public ResponseData<?> createPost(@RequestBody CreatePostDto createPostDto) {
        return this.postService.createPost(createPostDto);
    }

    /**
     * userga tegishli hamma postlarni olish uchun ishlatiladi
     * bunda juda kop malumot qaytishi mumkin va buni oldini olish uchun pageable qilingan
     *
     * @param page--        qaysi pageni olib kelishni korsatadi(default holatda 0)
     * @param size--1       ta page da nechta malumot bolishi kerakligini korsatadi(default holatda 10 ta)
     * @param userId--qaysi userning postlarini olib kelish kerakligini korsatadi (kiritish majburiy emas
     *                      agar kiritilsa kiritilgan userId biyicha qidiradi agar userId kiritilmagan bolsa hozzirgi sessiondagi userId boyicha qidirib keladi)
     */
    @GetMapping("get-all-own-posts")
    public ResponseData<?> getAllOwnPosts(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(required = false) UUID userId) {
        return this.postService.getAllOwnPosts(page, size, userId);
    }

    /**
     * 1 ta postni olib kelib berish uchun ishlatiladi
     */
    @GetMapping("get-one-by-id/{id}")
    public ResponseData<?> getOnePostById(@PathVariable UUID id) {
        return this.postService.getOneById(id);
    }

    /**
     * 1 ta postni o'chirish uchun ishlatiladi(agar hozzirgis session dagi user bilan DB shu postdagi user bir xil bolsa)
     */
    @DeleteMapping("delete-one-by-id/{id}")
    public ResponseData<?> deleteOnePostById(@PathVariable UUID id) {
        return this.postService.deleteOneById(id);
    }

    /**
     * oziga tegishli hamma postlarni yo'q qilish
     */
    @DeleteMapping("delete-all-own-posts")
    public ResponseData<?> deleteAllOwnPosts() {
        return this.postService.deleteAll();
    }

    /**
     * 1 ta postni update qilish yoliga uzatadi
     */
    @PutMapping("update-one-by-id")
    public ResponseData<?> update(@RequestBody PostGetDto postGetDto) {
        return this.postService.updateOneById(postGetDto);
    }

}
