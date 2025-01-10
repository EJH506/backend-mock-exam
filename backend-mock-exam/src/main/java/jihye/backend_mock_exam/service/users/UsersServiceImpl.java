package jihye.backend_mock_exam.service.users;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.passwordEncode.PasswordEncoderUtil;
import jihye.backend_mock_exam.service.users.dto.EditAccountDto;
import jihye.backend_mock_exam.repository.users.UsersRepository;
import jihye.backend_mock_exam.repository.users.UsersSearchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoderUtil passwordEncoderUtil;

    // 회원정보수정
    @Override
    public boolean editAccount(Long userId, EditAccountDto dto) {

        // 비밀번호 검사
        String DBPassword = userInfo(userId).orElseThrow().getPassword();
        boolean matches = passwordEncoderUtil.matches(dto.getPassword(), DBPassword);

        if (matches) {
            usersRepository.userUpdate(userId, dto);
        }

        return matches;
    }

    // 회원정보(식별자)
    @Override
    public Optional<User> userInfo(Long userId) {
        return usersRepository.findUserById(userId);
    }

    // 회원목록조회
    @Override
    public List<User> userList(UsersSearchCond cond) {
        return List.of();
    }

    // 회원탈퇴
    @Override
    public void deleteAccount(Long userId, String password) {

    }
}
