package jihye.backend_mock_exam.repository.user;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.user.DTO.SignUpDTO;
import jihye.backend_mock_exam.repository.user.DTO.UserUpdateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    // 회원저장
    User userSave(SignUpDTO dto);

    // 회원정보 업데이트
    void userUpdate(@Param("id") Long id, @Param("userUpdateDTO") UserUpdateDTO dto);

    // 아이디로 회원조회
    Optional<User> findUserById(Long id);

    // 회원 전체 조회
    List<User> findAllUsers(UserSearchCond cond);

    // 회원삭제
    void userRemove(@Param("id") Long id, @Param("password") String password);
}
