package jihye.backend_mock_exam.service.auth;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.auth.AuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
//@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    public CustomUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {

        User findByIdUser = authRepository.findByLoginId(accountId);
        log.info("findByIdUser={}", findByIdUser);

        if (findByIdUser == null) {
            throw new UsernameNotFoundException("User not found with username: " + accountId);
        }

        return new User(
                findByIdUser.getAccountId(),
                findByIdUser.getHashedPassword(),
                findByIdUser.getRoles()
        );
    }

}
