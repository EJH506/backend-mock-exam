package jihye.backend_mock_exam.service.users;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.users.dto.UserUpdateDTO;
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

    // 회원정보수정
    @Override
    public void editAccount(Long userId, UserUpdateDTO dto) {

    }

    // 회원정보(식별자)
    @Override
    public Optional<User> userInfo(Long userId) {
        return Optional.empty();
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
