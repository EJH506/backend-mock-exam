package jihye.backend_mock_exam.repository.auth;

import jihye.backend_mock_exam.domain.user.User;

public interface AuthRepository {

    // 회원저장
    User userSave(User user);

    // 아이디 존재 여부 조회
    boolean isIdExists(String accountId);

    // 로그인 데이터와 일치하는 회원 조회
    User findByLoginId(String accountId);

}
