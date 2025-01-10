package jihye.backend_mock_exam.controller.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jihye.backend_mock_exam.controller.auth.validation.ForgotPasswordIdValidator;
import jihye.backend_mock_exam.controller.auth.validation.ForgotPasswordQuestionValidator;
import jihye.backend_mock_exam.controller.auth.validation.ResetPasswordValidator;
import jihye.backend_mock_exam.controller.auth.validation.SignUpValidator;
import jihye.backend_mock_exam.domain.user.FindPasswordQuestions;
import jihye.backend_mock_exam.domain.user.Guest;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.auth.dto.*;
import jihye.backend_mock_exam.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final SignUpValidator signUpValidator;
    private final ForgotPasswordIdValidator forgotPasswordIdValidator;
    private final ForgotPasswordQuestionValidator forgotPasswordQuestionValidator;
    private final ResetPasswordValidator resetPasswordValidator;

    // 회원가입 페이지
    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        model.addAttribute("findPasswordQuestions", FindPasswordQuestions.values());
        model.addAttribute("signUpDto", new SignUpDto());
        return "auth/signup";
    }

    // 회원가입 처리
    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute SignUpDto dto, BindingResult bindingResult, HttpSession session, Model model) {

        // 검증 (유효성-정규식, 중복 여부)
        signUpValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            model.addAttribute("findPasswordQuestions", FindPasswordQuestions.values());
            return "auth/signup";
        }

        User savedUser = authService.signUp(dto);
        
        // 로그인 시 자동입력 되어있기 위한 세션
        session.setAttribute("accountId", savedUser.getAccountId());
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
    public String signupSuccess() {
        return "auth/signup-success";
    }



    // 로그인 페이지
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



    // 회원탈퇴 시 리다이렉트 되는 페이지
    @GetMapping("/sign-out")
    public String signOutPage() {
        return "users/delete-success";
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

    // 비회원으로 시작 처리
    @PostMapping("/guest-start")
    public String guestStart(@ModelAttribute GuestStartDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        Guest guest = authService.guestStart(dto.getNickname());

        HttpSession session = request.getSession();
        session.setAttribute("guest", guest);

        return "redirect:/";
    }


    // 비밀번호 찾기 페이지 진입
    @GetMapping("/forgot-password")
    public String forgotPasswordRedirect() {
        return "redirect:/auth/forgot-password-id";
    }

    // 비밀번호 찾기 아이디입력 페이지
    @GetMapping("/forgot-password-id")
    public String forgotPasswordIdPage(Model model) {
        model.addAttribute("forgotPasswordIdDto", new ForgotPasswordIdDto());
        return "auth/forgot-password-id";
    }
    
    // 비밀번호 찾기 아이디입력 처리
    @PostMapping("/forgot-password-id")
    public String forgotPasswordId(@Valid @ModelAttribute ForgotPasswordIdDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        // 검증 (유효성, 존재유무)
        forgotPasswordIdValidator.validate(dto, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "auth/forgot-password-id";
        }

        redirectAttributes.addAttribute("accountId", dto.getAccountId());

        return "redirect:/auth/forgot-password-question";
    }

    // 비밀번호 찾기 질문 페이지
    @GetMapping("/forgot-password-question")
    public String forgotPasswordQuestionPage(Model model) {

        model.addAttribute("findPasswordQuestions", FindPasswordQuestions.values());
        model.addAttribute("forgotPasswordQuestionDto", new ForgotPasswordQuestionDto());

        return "auth/forgot-password-question";
    }

    // 비밀번호 찾기 질문 처리
    @PostMapping("/forgot-password-question")
    public String forgotPasswordQuestion(@RequestParam("accountId") String accountId,
                                         @Valid @ModelAttribute() ForgotPasswordQuestionDto dto,
                                         BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes,
                                         Model model) {


        dto.setAccountId(accountId);
        forgotPasswordQuestionValidator.validate(dto, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            model.addAttribute("findPasswordQuestions", FindPasswordQuestions.values());
            return "auth/forgot-password-question";
        }

        redirectAttributes.addAttribute("accountId", accountId);
        return "redirect:/auth/reset-password";
    }

    // 비밀번호 재설정 페이지
    @GetMapping("/reset-password")
    public String resetPasswordPage(@RequestParam("accountId") String accountId, Model model) {

        model.addAttribute("resetPasswordDto", new ResetPasswordDto());
        return "auth/reset-password";
    }

    // 비밀번호 재설정 처리
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("accountId") String accountId,
                                @Valid @ModelAttribute ResetPasswordDto dto,
                                BindingResult bindingResult, HttpServletRequest request) {

        dto.setAccountId(accountId);
        resetPasswordValidator.validate(dto, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "auth/reset-password";
        }

        HttpSession session = request.getSession();

        User updateUser = authService.resetPassword(accountId, dto.getPassword());

        // 로그인 시 자동입력 되어있기 위한 세션
        session.setAttribute("accountId", updateUser.getAccountId());

        return "redirect:/auth/reset-password-success";
    }

    // 비밀번호 재설정 성공 페이지
    @GetMapping("/reset-password-success")
    public String resetPasswordSuccess() {
        return "auth/reset-password-success";
    }

}
