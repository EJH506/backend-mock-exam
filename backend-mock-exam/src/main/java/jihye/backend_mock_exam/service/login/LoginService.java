package jihye.backend_mock_exam.service.login;

import jihye.backend_mock_exam.domain.user.User;

public interface LoginService {

    User login(String accountId, String password);

}
