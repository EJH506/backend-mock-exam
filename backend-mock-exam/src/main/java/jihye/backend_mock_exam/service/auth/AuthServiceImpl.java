package jihye.backend_mock_exam.service.auth;

import jihye.backend_mock_exam.domain.user.Guest;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.auth.AuthRepository;
import jihye.backend_mock_exam.service.auth.dto.SignUpDto;
import jihye.backend_mock_exam.service.passwordEncode.PasswordEncoderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.management.remote.JMXAuthenticator;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoderUtil passwordEncoderUtil;
    private final AuthenticationManager authenticationManager;

    // 회원가입
    @Override
    public User signUp(SignUpDto dto) {
        // 비밀번호 암호화
        String hashedPassword = passwordEncoderUtil.encode(dto.getPassword());

        User user = new User(dto.getAccountId(), dto.getNickname(), hashedPassword, dto.getFindPasswordQuestion(), dto.getFindPasswordAnswer(), dto.getGender(), dto.getBirthYear(), dto.getRoles());
        user.setRoles(RoleConst.ROLE_USER);

        return authRepository.userSave(user);
    }

    // 회원가입 - 아이디 중복검사
    @Override
    public boolean validateDuplicateId(String accountId) {
        return authRepository.isIdExists(accountId);
    }

    // 로그인
    // 스프링 시큐리티 사용으로 사용안함
    @Override
    public User login(String accountId, String password) {

        User findByIdUser = authRepository.findByLoginId(accountId);
        String hashedPassword = findByIdUser != null ? findByIdUser.getHashedPassword() : "";

        boolean matches = passwordEncoderUtil.matches(password, hashedPassword);

        if (!matches) {
            return null;
        }

        return findByIdUser;
    }

    // 비회원으로 시작
    @Override
    public Guest guestStart(String nickname) {
        return new Guest(nickname);
    }
}
