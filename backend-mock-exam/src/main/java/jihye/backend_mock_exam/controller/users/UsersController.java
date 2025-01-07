package jihye.backend_mock_exam.controller.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    // 비밀번호 찾기
    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "users/forgot-password";
    }
}
