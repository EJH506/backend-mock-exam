package jihye.backend_mock_exam.repository.user;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.user.DTO.SignUpDTO;
import jihye.backend_mock_exam.repository.user.DTO.UserUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    private final UserMapper userMapper;

    // 회원저장
    @Override
    public User userSave(SignUpDTO dto) {
        return userMapper.userSave(dto);
    }

    // 회원정보 업데이트
    @Override
    public void userUpdate(Long id, UserUpdateDTO dto) {

    }

    // 아이디로 회원조회
    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.empty();
    }

    // 회원 전체 조회
    @Override
    public List<User> findAllUsers(UserSearchCond cond) {
        return List.of();
    }

    // 회원삭제
    @Override
    public void userRemove(Long id, String password) {

    }
}
