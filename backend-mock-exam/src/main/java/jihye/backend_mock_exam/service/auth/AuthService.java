package jihye.backend_mock_exam.service.auth;

import jakarta.servlet.http.HttpServletRequest;
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

    // 수동 로그아웃 처리
    void logout(HttpServletRequest request);

    // 비회원으로 시작
    Guest guestStart(String nickname);

    // 비밀번호 찾기
    User findPasswordId(String accountId);

    // 비밀번호 재설정
    User resetPassword(String accountId, String password);
}
