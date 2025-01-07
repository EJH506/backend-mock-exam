package jihye.backend_mock_exam.controller.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.user.DTO.SignInDTO;
import jihye.backend_mock_exam.service.login.LoginService;
import jihye.backend_mock_exam.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // 로그인 페이지
    @GetMapping("/sign-in")
    public String signInPage(@RequestParam(value = "accountId", required = false) String accountId, Model model) {
        model.addAttribute("signInDTO", new SignInDTO(accountId));
        return "login/signin";
    }

    // 로그인 처리
    @PostMapping("/sign-in")
    public String signIn(@Valid @ModelAttribute SignInDTO dto, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/signin";
        }

        User loginUser = loginService.login(dto.getAccountId(), dto.getPassword());

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login/signin";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginUser", loginUser);

        return "redirect:/"; // 원래있던 페이지로
    }

    // 로그아웃 처리
    @PostMapping("/sign-out")
    public String signOut(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
