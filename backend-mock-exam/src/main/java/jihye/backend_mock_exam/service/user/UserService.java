package jihye.backend_mock_exam.service.user;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.user.DTO.SignUpDTO;
import jihye.backend_mock_exam.repository.user.DTO.UserUpdateDTO;
import jihye.backend_mock_exam.repository.user.UserRepository;
import jihye.backend_mock_exam.repository.user.UserSearchCond;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // 회원가입
    User signUp(SignUpDTO dto);

    // 회원정보수정
    void editAccount(Long accountId, UserUpdateDTO dto);

    // 회원정보
    Optional<User> userInfo(Long accountId);

    // 회원목록조회
    List<User> userList(UserSearchCond cond);

    // 회원탈퇴
    void deleteAccount(Long accountId, String password);

}
