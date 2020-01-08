package me.right.study.post.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.right.study.post.domain.dto.PostResponseDto;
import me.right.study.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postService.findAll(PageRequest.of(0, 10)));

        return "index";

    }

    @GetMapping("/posts")
    @ResponseBody
    public Page<PostResponseDto> posts(){
        return postService.findAll(PageRequest.of(0, 10));
    }

}
