package jihye.backend_mock_exam.repository.users;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.users.dto.EditAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsersRepositoryImpl implements UsersRepository {

    private final UsersMapper usersMapper;


    // 회원정보 업데이트
    @Override
    public void userUpdate(Long userId, EditAccountDto dto) {
        usersMapper.userUpdate(userId, dto);
    }

    // 식별자로 회원조회
    @Override
    public Optional<User> findUserById(Long userId) {
        return usersMapper.findUserById(userId);
    }

    // 회원 전체 조회
    @Override
    public List<User> findAllUsers(UsersSearchCond cond) {
        return List.of();
    }

    // 회원삭제
    @Override
    public void userRemove(Long userId, String hashedPassword) {

    }
}
