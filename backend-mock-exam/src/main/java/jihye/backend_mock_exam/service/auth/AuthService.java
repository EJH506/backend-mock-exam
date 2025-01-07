package jihye.backend_mock_exam.service.auth;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.users.dto.SignUpDTO;

public interface AuthService {

    // 회원가입
    User signUp(SignUpDTO dto);

    // 아이디중복검사
    boolean validateDuplicateId(String accountId);

    // 로그인
    User login(String accountId, String password);

}
