package jihye.backend_mock_exam.repository.login;

import jihye.backend_mock_exam.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
    
    // 로그인 데이터와 일치하는 회원 조회
    User findByLoginId(String accountId);
    User findByLoginData(@Param("accountId") String accountId, @Param("password") String password);

}
