package jihye.backend_mock_exam.repository.login;

import jihye.backend_mock_exam.domain.user.User;

public interface LoginRepository {

    // 로그인 데이터와 일치하는 회원 조회
    User findByLoginData(String accountId, String password);

    // 로그인 세션 제거
    void invalidateUserSession(Long userId);
}
