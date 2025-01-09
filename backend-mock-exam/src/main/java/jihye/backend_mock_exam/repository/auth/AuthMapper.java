package jihye.backend_mock_exam.repository.auth;

import jihye.backend_mock_exam.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthMapper {

    // 회원저장
    void userSave(User user);

    // 아이디 존재 여부 조회
    boolean isIdExists(String accountId);
    
    // 요청 아이디와 일치하는 회원 조회
    User findByLoginId(String accountId);

    User findById(Long userId);

    // 비밀번호 재설정
    void updatePassword(@Param("userId") Long userId, @Param("hashedPassword") String hashedPassword);
}
