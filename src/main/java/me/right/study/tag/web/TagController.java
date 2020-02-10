package me.right.study.tag.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.right.study.tag.dto.TagRequestDto;
import me.right.study.tag.dto.TagResponseDto;
import me.right.study.tag.service.TagService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/tags")
    public Result tags(@RequestParam(name = "q", required = false) String keyword) {
        List<TagResponseDto> tags = tagService.findAll(keyword);

        return new Result<>(tags, tags.size());
    }

    @PostMapping("/tags")
    public Map<String, Long> createTag(@RequestBody @Valid TagRequestDto dto) {
        Long id = tagService.save(dto);

        return Map.of("id", id);
    }

    @DeleteMapping("/tags/{id}")
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
