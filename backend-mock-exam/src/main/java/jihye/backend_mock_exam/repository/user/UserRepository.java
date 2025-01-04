package jihye.backend_mock_exam.repository.user;

import jihye.backend_mock_exam.repository.user.DTO.SignUpDTO;
import jihye.backend_mock_exam.repository.user.DTO.UserUpdateDTO;
import jihye.backend_mock_exam.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    // 회원저장
    User userSave(User user);

    // 회원정보 업데이트
    void userUpdate(Long userId, UserUpdateDTO dto);

    // 식별자로 회원조회
    Optional<User> findUserById(Long userId);

    // 아이디 존재 여부 조회
    boolean isIdExists(String accountId);

    // 회원 전체 조회
    List<User> findAllUsers(UserSearchCond cond);

    // 회원삭제
    void userRemove(Long userId, String passwordHash);
}
