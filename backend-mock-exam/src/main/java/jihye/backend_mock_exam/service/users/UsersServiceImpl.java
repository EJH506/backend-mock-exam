package jihye.backend_mock_exam.service.users;

import jakarta.servlet.http.HttpServletRequest;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.auth.AuthService;
import jihye.backend_mock_exam.service.passwordEncode.PasswordEncoderUtil;
import jihye.backend_mock_exam.service.users.dto.DeleteAccountDto;
import jihye.backend_mock_exam.service.users.dto.EditAccountDto;
import jihye.backend_mock_exam.repository.users.UsersRepository;
import jihye.backend_mock_exam.repository.users.UsersSearchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoderUtil passwordEncoderUtil;
    private final UserDetailsService userDetailsService;
    private final AuthService authService;

    // 회원정보수정
    @Override
    public boolean editAccount(Long userId, EditAccountDto dto) {

        // 비밀번호 검사
        String DBPassword = userInfo(userId).orElseThrow().getPassword();
        boolean matches = passwordEncoderUtil.matches(dto.getPassword(), DBPassword);

        if (matches) {
            usersRepository.userUpdate(userId, dto);
        }

        renewalAuthentication(userId);

        return matches;
    }

    // 수정 정보를 반영하여 재 로그인 처리
    private void renewalAuthentication(Long userId) {

        User updatedUser = userInfo(userId).orElseThrow();

        // UserDetailsService로 사용자정보 가져오기
        UserDetails updatedUserDetails = userDetailsService.loadUserByUsername(updatedUser.getUsername());

        // 새로운 인증객체생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(updatedUserDetails, updatedUser.getPassword(), updatedUserDetails.getAuthorities());

        // 새 인증정보를 SecurityContext에 설정하여 재인증 처리
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    // 회원정보(식별자)
    @Override
    public Optional<User> userInfo(Long userId) {
        return usersRepository.findUserById(userId);
    }

    // 회원목록조회
    @Override
    public List<User> userList(UsersSearchCond cond) {
        return List.of();
    }

    // 회원탈퇴
    @Override
    public boolean deleteAccount(Long userId, DeleteAccountDto dto, HttpServletRequest request) {

        // 비밀번호 검사
        String DBPassword = userInfo(userId).orElseThrow().getPassword();
        boolean matches = passwordEncoderUtil.matches(dto.getPassword(), DBPassword);

        if (matches) {
            usersRepository.userRemove(userId);
            // 수동 로그아웃 처리
            authService.logout(request);
        }

        return matches;
    }
}
