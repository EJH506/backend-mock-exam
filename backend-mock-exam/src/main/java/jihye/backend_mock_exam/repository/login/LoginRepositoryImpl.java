package jihye.backend_mock_exam.repository.login;

import jihye.backend_mock_exam.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoginRepositoryImpl implements LoginRepository {

    private final LoginMapper loginMapper;

    @Override
    public User findByLoginData(String accountId, String password) {
        return loginMapper.findByLoginData(accountId, password);
    }

    @Override
    public void invalidateUserSession(Long userId) {
    }

}
