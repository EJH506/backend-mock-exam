package jihye.backend_mock_exam.service.auth;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.auth.AuthRepository;
import jihye.backend_mock_exam.repository.users.dto.SignUpDTO;
import jihye.backend_mock_exam.service.passwordEncode.PasswordEncoderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoderUtil passwordEncoderUtil;

    // 회원가입
    @Override
    public User signUp(SignUpDTO dto) {
        // 비밀번호 암호화
        String hashedPassword = passwordEncoderUtil.encode(dto.getPassword());

        User user = new User(dto.getAccountId(), dto.getNickname(), hashedPassword, dto.getFindPasswordQuestion(), dto.getFindPasswordAnswer(), dto.getGender(), dto.getBirthYear());
        return authRepository.userSave(user);
    }

    // 아이디중복검사
    @Override
    public boolean validateDuplicateId(String accountId) {
        return authRepository.isIdExists(accountId);
    }


    @Override
    public User login(String accountId, String password) {

        User findByIdUser = authRepository.findByLoginId(accountId);
        boolean matches = passwordEncoderUtil.matches(password, findByIdUser.getHashedPassword());

        if (!matches) {
            return null;
        }

        return findByIdUser;
    }
}
