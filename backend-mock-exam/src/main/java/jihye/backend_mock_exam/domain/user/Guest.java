package jihye.backend_mock_exam.domain.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class Guest implements Role {

    private Long userId;
    private String nickname;

    public Guest() {
    }

    public Guest(String nickname) {
        this.userId = 0L;
        this.nickname = nickname;
    }

}
