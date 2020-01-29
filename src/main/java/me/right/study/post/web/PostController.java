package me.right.study.post.web;

import lombok.RequiredArgsConstructor;
import me.right.study.post.domain.dto.PostRequestDto;
import me.right.study.post.domain.dto.PostResponseDto;
import me.right.study.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postService.findAll(PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdDate"))));

        return "index";
    }

    @GetMapping("/posts/{page}")
    public String posts(Model model, @PathVariable Integer page){
        model.addAttribute("posts", postService.findAll(PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "createdDate"))));

        return "index";
    }

    @GetMapping("/post/{id}")
    public String view(@PathVariable Long id, Model model){
        model.addAttribute("post", postService.findOne(id));
        return "post/view";
    }

    @GetMapping("/posts")
    @ResponseBody
    public Page<PostResponseDto> posts(){
        return postService.findAll(PageRequest.of(0, 10));
    }

    @GetMapping("/posts/new")
    public String newPost(){
        return "post/new";
    }

    @PostMapping("/posts")
    public String createPost(@ModelAttribute @Valid PostRequestDto postRequestDto){
        postService.save(postRequestDto);

        return "redirect:/";
    }

}
