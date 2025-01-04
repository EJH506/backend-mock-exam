package jihye.backend_mock_exam.repository.user;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.user.DTO.UserUpdateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    // 회원저장
    void userSave(User user);

    // 회원정보 업데이트
    void userUpdate(@Param("userId") Long userId, @Param("userUpdateDTO") UserUpdateDTO dto);

    // 식별자로 회원조회
    Optional<User> findUserById(Long userId);

    // 아이디 존재 여부 조회
    boolean isIdExists(String accountId);

    // 회원 전체 조회
    List<User> findAllUsers(UserSearchCond cond);

    // 회원삭제
    void userRemove(@Param("userId") Long userId, @Param("passwordHash") String passwordHash);
}
