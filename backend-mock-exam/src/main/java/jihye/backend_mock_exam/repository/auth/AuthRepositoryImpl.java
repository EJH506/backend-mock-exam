package jihye.backend_mock_exam.repository.auth;

import jihye.backend_mock_exam.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
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

    // 요청 아이디와 일치하는 회원 조회
    @Override
    public User findByAccountId(String accountId) {
        return authMapper.findByLoginId(accountId);
    }

    @Override
    public User findById(Long userId) {
        return authMapper.findById(userId);
    }

    // 비밀번호 재설정
    @Override
    public User updatePassword(Long userId, String hashedPassword) {
        authMapper.updatePassword(userId, hashedPassword);
        return findById(userId);
    }
}
