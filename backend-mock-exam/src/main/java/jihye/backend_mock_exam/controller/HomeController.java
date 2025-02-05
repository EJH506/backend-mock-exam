package jihye.backend_mock_exam.controller;

import jihye.backend_mock_exam.domain.user.Guest;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.users.UsersRepository;
import jihye.backend_mock_exam.service.auth.AuthService;
import jihye.backend_mock_exam.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AuthService authService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user,
                       @SessionAttribute(value = "guest", required = false) Guest guest,
                       Model model) {

        model.addAttribute("themeColor", "#71B961");

        // 비회원 변경
        if (user == null && guest == null) {
            return "welcome";
        }

        return "home";
    }
}
