package jihye.backend_mock_exam.service.auth;

import jihye.backend_mock_exam.domain.user.Guest;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.auth.AuthRepository;
import jihye.backend_mock_exam.service.auth.dto.SignUpDto;
import jihye.backend_mock_exam.service.passwordEncode.PasswordEncoderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoderUtil passwordEncoderUtil;

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

        User findByIdUser = authRepository.findByAccountId(accountId);
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

    // 비밀번호 찾기 아이디입력
    @Override
    public User findPasswordId(String accountId) {
        return authRepository.findByAccountId(accountId);
    }

    // 비밀번호 재설정

    @Override
    public User resetPassword(String accountId, String password) {
        // 비밀번호 암호화
        log.info("비밀번호 암호화 전");
        String hashedPassword = passwordEncoderUtil.encode(password);
        log.info("hashedPassword={}", hashedPassword);

        User user = authRepository.findByAccountId(accountId);
        authRepository.updatePassword(user.getUserId(), hashedPassword);

        return user;
    }
}
