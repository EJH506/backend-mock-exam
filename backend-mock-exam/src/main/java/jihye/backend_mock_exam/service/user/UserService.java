package jihye.backend_mock_exam.service.user;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.user.DTO.SignUpDTO;
import jihye.backend_mock_exam.repository.user.DTO.UserUpdateDTO;
import jihye.backend_mock_exam.repository.user.UserSearchCond;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // 회원가입
    User signUp(SignUpDTO dto);

    // 회원정보수정
    void editAccount(Long userId, UserUpdateDTO dto);

    // 회원정보(식별자)
    Optional<User> userInfo(Long userId);

    // 아이디중복검사
    boolean validateDuplicateId(String accountId);

    // 회원목록조회
    List<User> userList(UserSearchCond cond);

    // 회원탈퇴
    void deleteAccount(Long userId, String password);

}
