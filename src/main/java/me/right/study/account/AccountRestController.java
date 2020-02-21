package me.right.study.account;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountService accountService;

    @PostMapping("/api/v1/users")
    public Map<String, String> createUser(@RequestBody @Valid AccountDto accountDto) {
        String username =accountService.save(accountDto);

        return Map.of("id", username);
    }
}
