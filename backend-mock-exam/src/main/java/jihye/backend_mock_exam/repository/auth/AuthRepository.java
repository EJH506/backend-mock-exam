package jihye.backend_mock_exam.repository.auth;

import jihye.backend_mock_exam.domain.user.User;

public interface AuthRepository {

    // 회원저장
    User userSave(User user);

    // 아이디 존재 여부 조회
    boolean isIdExists(String accountId);

    // 요청 아이디와 일치하는 회원 조회
    User findByAccountId(String accountId);

    // 비밀번호 재설정
    User updatePassword(String accountId, String hashedPassword);
}
