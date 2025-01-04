package jihye.backend_mock_exam.controller.member;

import jakarta.validation.Valid;
import jihye.backend_mock_exam.controller.validation.UserValidator;
import jihye.backend_mock_exam.domain.user.FindPasswordQuestions;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.user.DTO.SignUpDTO;
import jihye.backend_mock_exam.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {

    private final UserService userService;
    private final UserValidator userValidator;

    // 비밀번호 찾기 질문 목록
    @ModelAttribute("findPasswordQuestions")
    public FindPasswordQuestions[] findPasswordQuestions() {
        return FindPasswordQuestions.values();
    }

    // 회원가입 페이지
    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        model.addAttribute("signUpDTO", new SignUpDTO());
        return "user/signup";
    }

    // 회원가입 처리
    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute SignUpDTO dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        // 유효성 검사 (정규식, 중복)
        userValidator.validate(dto, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "user/signup";
        }

        User savedUser = userService.signUp(dto);
        redirectAttributes.addAttribute("userId", savedUser.getUserId());
        return "redirect:/users/signup-success";
    }

    // 아이디 중복 검사 AJAX
    @PostMapping("/check-id")
    @ResponseBody
    public boolean checkAccountId(@RequestParam("accountId") String accountId) {
        return userService.validateDuplicateId(accountId);
    }

    // 회원가입 성공 페이지
    @GetMapping("/signup-success")
    public String signupSuccess() {
        return "user/signup-success";
    }

}
