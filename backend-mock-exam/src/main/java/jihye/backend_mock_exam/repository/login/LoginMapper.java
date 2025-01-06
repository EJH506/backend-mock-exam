package jihye.backend_mock_exam.repository.login;

import jihye.backend_mock_exam.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    
    // 로그인 데이터와 일치하는 회원 조회
    User findByLoginData(String accountId, String password);

}
