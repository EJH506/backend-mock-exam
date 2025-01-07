package jihye.backend_mock_exam.repository.auth;

import jihye.backend_mock_exam.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepository {

    private final AuthMapper authMapper;

    // 회원저장
    @Override
    public User userSave(User user) {
        authMapper.userSave(user);
        return user;
    }

    // 아이디 존재 여부 조회
    @Override
    public boolean isIdExists(String accountId) {
        return authMapper.isIdExists(accountId);
    }

    // 로그인 데이터와 일치하는 회원 조회
    @Override
    public User findByLoginId(String accountId) {
        return authMapper.findByLoginId(accountId);
    }


}
