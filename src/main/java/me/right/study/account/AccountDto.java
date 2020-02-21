package me.right.study.account;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class AccountDto extends User {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public AccountDto(Account account){
        super(account.getUsername(), account.getPassword(), List.of(new SimpleGrantedAuthority("ADMIN")));
        this.username = account.getUsername();
        this.password = account.getPassword();
    }

    public AccountDto(String username, String password) {
        super(username, password, List.of(new SimpleGrantedAuthority("ADMIN")));
        this.username = username;
        this.password = password;
    }

    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public Account toEntity(){
        return Account.builder()
                        .username(this.username)
                        .password(this.password)
                        .build()
                ;
    }
}
