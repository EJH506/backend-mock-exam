package jihye.backend_mock_exam.repository.login;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jihye.backend_mock_exam.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoginRepositoryImpl implements LoginRepository {

    private final LoginMapper loginMapper;

    @Override
    public User findByLoginId(String accountId) {
        return loginMapper.findByLoginId(accountId);
    }

    @Override
    public User findByLoginData(String accountId, String password) {
        return loginMapper.findByLoginData(accountId, password);
    }

}
