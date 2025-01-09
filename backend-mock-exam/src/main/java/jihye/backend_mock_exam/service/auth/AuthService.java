package jihye.backend_mock_exam.service.auth;

import jihye.backend_mock_exam.domain.user.Guest;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.auth.dto.SignUpDto;

public interface AuthService {

    // 회원가입
    User signUp(SignUpDto dto);

    // 아이디중복검사
    boolean validateDuplicateId(String accountId);

    // 로그인
    User login(String accountId, String password);

    // 비회원으로 시작
    Guest guestStart(String nickname);
}
