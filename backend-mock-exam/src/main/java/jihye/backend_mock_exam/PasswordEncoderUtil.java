package jihye.backend_mock_exam;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderUtil {

    private final PasswordEncoder passwordEncoder;

    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword); // 비밀번호 암호화
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword); // 암호화된 비밀번호와 비교
    }
}
