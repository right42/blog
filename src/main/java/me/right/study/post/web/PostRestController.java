package me.right.study.post.web;

import lombok.RequiredArgsConstructor;
import me.right.study.post.dto.PostRequestDto;
import me.right.study.post.service.PostService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;

    @PostMapping("/api/v1/post")
    public Long save(@RequestBody @Valid PostRequestDto postRequestDto, BindingResult result){

        if (result.hasErrors()) {

        }

        return postService.save(postRequestDto);
    }

}
