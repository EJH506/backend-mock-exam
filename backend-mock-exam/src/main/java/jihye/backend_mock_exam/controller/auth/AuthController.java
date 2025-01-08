package jihye.backend_mock_exam.controller.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jihye.backend_mock_exam.controller.validation.UserValidator;
import jihye.backend_mock_exam.domain.user.FindPasswordQuestions;
import jihye.backend_mock_exam.domain.user.Guest;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.auth.dto.GuestStartDto;
import jihye.backend_mock_exam.service.auth.dto.SignInDto;
import jihye.backend_mock_exam.service.auth.dto.SignUpDto;
import jihye.backend_mock_exam.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserValidator userValidator;

    // 비밀번호 찾기 질문 목록
    @ModelAttribute("findPasswordQuestions")
    public FindPasswordQuestions[] findPasswordQuestions() {
        return FindPasswordQuestions.values();
    }

    // 회원가입 페이지
    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        model.addAttribute("signUpDto", new SignUpDto());
        return "auth/signup";
    }


    // 회원가입 처리
    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute SignUpDto dto, BindingResult bindingResult, HttpSession session) {

        // 유효성 검사 (정규식, 중복)
        userValidator.validate(dto, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "auth/signup";
        }

        User savedUser = authService.signUp(dto);
        session.setAttribute("accountId", savedUser.getAccountId()); // 로그인 스프링 시큐리티 사용으로 사용중지
        return "redirect:/auth/signup-success";
    }

    // 아이디 중복 검사 AJAX
    @PostMapping("/check-id")
    @ResponseBody
    public boolean checkAccountId(@RequestParam("accountId") String accountId) {
        return authService.validateDuplicateId(accountId);
    }

    // 회원가입 성공 페이지
    @GetMapping("/signup-success")
    public String signupSuccess(@ModelAttribute("accountId") String accountId, Model model) {
        return "auth/signup-success";
    }

/*
    // 로그인 페이지
    @GetMapping("/sign-in")
    public String signInPage(@SessionAttribute(value = "accountId", required = false) String sessionId, Model model) {
        if (sessionId != null) {
            model.addAttribute("signInDto", new SignInDto(sessionId));
        } else {
            model.addAttribute("signInDto", new SignInDto());
        }

        return "auth/signin";
    }

 */

    @GetMapping("/sign-in")
    public String signInPage(@RequestParam(value = "error", required = false) Boolean error,
                             @SessionAttribute(value = "accountId", required = false) String sessionId,
                             @ModelAttribute SignInDto dto,
                             BindingResult bindingResult,
                             Model model) {

        if (sessionId != null) {
            dto.setAccountId(sessionId);
        }

        if (error != null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        return "auth/signin";
    }

    // 로그인 처리 (스프링 시큐리티 사용으로 사용중지)
    // @PostMapping("/sign-in")
    public String signIn(@Valid @ModelAttribute SignInDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "auth/signin";
        }

        log.info("loginId={}", dto.getAccountId());

        User loginUser = authService.login(dto.getAccountId(), dto.getPassword());

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "auth/signin";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginUser", loginUser);

        session.removeAttribute("accountId");

        return "redirect:/"; // 원래있던 페이지로 수정할 것
    }



    // 로그아웃 처리 (스프링 시큐리티 사용으로 사용중지)
    //@PostMapping("/sign-out")
    public String signOut(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }




    // 비회원으로 시작 페이지
    @GetMapping("/guest-start")
    public String guestStartPage(Model model) {
        model.addAttribute("GuestStartDto", new GuestStartDto());
        return "auth/guest-start";
    }

    @PostMapping("/guest-start")
    public String guestStart(@ModelAttribute GuestStartDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        Guest guest = authService.guestStart(dto.getNickname());

        HttpSession session = request.getSession();
        session.setAttribute("loginUser", guest);

        return "redirect:/";
    }

}
