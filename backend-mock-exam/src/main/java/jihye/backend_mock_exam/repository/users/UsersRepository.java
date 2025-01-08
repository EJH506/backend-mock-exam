package jihye.backend_mock_exam.repository.users;

import jihye.backend_mock_exam.service.users.dto.UserUpdateDto;
import jihye.backend_mock_exam.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {

    // 회원정보 업데이트
    void userUpdate(Long userId, UserUpdateDto dto);

    // 식별자로 회원조회
    Optional<User> findUserById(Long userId);

    // 회원 전체 조회
    List<User> findAllUsers(UsersSearchCond cond);

    // 회원삭제
    void userRemove(Long userId, String hashedPassword);
}
