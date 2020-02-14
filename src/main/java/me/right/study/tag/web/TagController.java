package me.right.study.tag.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.right.study.tag.dto.TagRequestDto;
import me.right.study.tag.dto.TagResponseDto;
import me.right.study.tag.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/tags")
    public String tagIndex(Model model){

        model.addAttribute("tagPosts", tagService.findAllWithPost());

        return "tag/index";
    }

    @GetMapping("/api/v1/tags")
    @ResponseBody
    public Result tags(@RequestParam(name = "q", required = false) String keyword) {
        List<TagResponseDto> tags = tagService.findAllByName(keyword);
        return new Result<>(tags, tags.size());
    }

    @PostMapping("/api/v1/tags")
    @ResponseBody
    public Map<String, Long> createTag(@RequestBody @Valid TagRequestDto dto) {
        Long id = tagService.save(dto);

        return Map.of("id", id);
    }

    @DeleteMapping("/api/v1/tags/{id}")
    @ResponseBody
    public Map<String, Long> deleteTag(@PathVariable Long id) {

        return Map.of("id", id);
    }

    @Data
    private class Result<T> {
        private int size = 1;
        private T content;

        public Result(T content) {
            this.content = content;
        }

        public Result(T content, int size) {
            this.content = content;
            this.size = size;
        }
    }
}
