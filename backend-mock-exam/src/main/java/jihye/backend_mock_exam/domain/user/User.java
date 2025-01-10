package jihye.backend_mock_exam.domain.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class User implements UserDetails {

    private Long userId;
    private String accountId;
    private String nickname;
    private String hashedPassword;
    private String findPasswordQuestion;
    private String findPasswordAnswer;
    private String gender;
    private Integer birthYear;
    private String roles;

    public User() {
    }

    public User(Long userId, String accountId, String nickname, String hashedPassword, String roles) {
        this.userId = userId;
        this.accountId = accountId;
        this.nickname = nickname;
        this.hashedPassword = hashedPassword;
        this.roles = roles;
    }

    public User(String accountId, String nickname, String hashedPassword, String findPasswordQuestion, String findPasswordAnswer, String gender, Integer birthYear, String roles) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.hashedPassword = hashedPassword;
        this.findPasswordQuestion = findPasswordQuestion;
        this.findPasswordAnswer = findPasswordAnswer;
        this.gender = gender;
        this.birthYear = birthYear;
        this.roles = roles;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(roles));
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public String getUsername() {
        return accountId;
    }


}
