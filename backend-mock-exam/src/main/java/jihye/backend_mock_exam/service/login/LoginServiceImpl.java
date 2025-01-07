package jihye.backend_mock_exam.service.login;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.login.LoginRepository;
import jihye.backend_mock_exam.repository.user.UserRepository;
import jihye.backend_mock_exam.service.passwordEncode.PasswordEncoderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    private final PasswordEncoderUtil passwordEncoderUtil;

    @Override
    public User login(String accountId, String password) {

        User findByIdUser = loginRepository.findByLoginId(accountId);
        boolean matches = passwordEncoderUtil.matches(password, findByIdUser.getHashedPassword());

        if (!matches) {
            return null;
        }

        return findByIdUser;
    }
}
