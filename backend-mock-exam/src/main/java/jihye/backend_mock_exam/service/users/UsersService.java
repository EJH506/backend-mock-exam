package jihye.backend_mock_exam.service.users;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.users.dto.DeleteAccountDto;
import jihye.backend_mock_exam.service.users.dto.EditAccountDto;
import jihye.backend_mock_exam.repository.users.UsersSearchCond;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    // 회원정보수정
    boolean editAccount(Long userId, EditAccountDto dto);

    // 회원정보(식별자)
    Optional<User> userInfo(Long userId);

    // 회원목록조회
    List<User> userList(UsersSearchCond cond);

    // 회원탈퇴
    boolean deleteAccount(Long userId, DeleteAccountDto dto);

}
