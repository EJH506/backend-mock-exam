package jihye.backend_mock_exam.service.user;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.user.DTO.SignUpDTO;
import jihye.backend_mock_exam.repository.user.DTO.UserUpdateDTO;
import jihye.backend_mock_exam.repository.user.UserRepository;
import jihye.backend_mock_exam.repository.user.UserSearchCond;
import jihye.backend_mock_exam.service.passwordEncode.PasswordEncoderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderUtil passwordEncoderUtil;

    // 회원가입
    @Override
    public User signUp(SignUpDTO dto) {
        // 비밀번호 암호화
        String hashedPassword = passwordEncoderUtil.encode(dto.getPassword());

        User user = new User(dto.getAccountId(), dto.getNickname(), hashedPassword, dto.getFindPasswordQuestion(), dto.getFindPasswordAnswer(), dto.getGender(), dto.getBirthYear());
        return userRepository.userSave(user);
    }

    // 회원정보수정
    @Override
    public void editAccount(Long userId, UserUpdateDTO dto) {

    }

    // 회원정보(식별자)
    @Override
    public Optional<User> userInfo(Long userId) {
        return Optional.empty();
    }

    // 아이디중복검사
    @Override
    public boolean validateDuplicateId(String accountId) {
        return userRepository.isIdExists(accountId);
    }

    // 회원목록조회
    @Override
    public List<User> userList(UserSearchCond cond) {
        return List.of();
    }

    // 회원탈퇴
    @Override
    public void deleteAccount(Long userId, String password) {

    }
}
