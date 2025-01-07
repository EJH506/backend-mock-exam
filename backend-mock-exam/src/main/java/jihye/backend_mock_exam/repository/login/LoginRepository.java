package jihye.backend_mock_exam.repository.login;

import jihye.backend_mock_exam.domain.user.User;

public interface LoginRepository {

    // 로그인 데이터와 일치하는 회원 조회
    User findByLoginId(String accountId);
    User findByLoginData(String accountId, String password);

}
