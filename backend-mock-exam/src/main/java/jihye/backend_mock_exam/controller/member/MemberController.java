package jihye.backend_mock_exam.controller.member;

import jakarta.validation.Valid;
import jihye.backend_mock_exam.domain.user.FindPasswordQuestions;
import jihye.backend_mock_exam.repository.user.DTO.SignUpDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/users")
public class MemberController {

    @ModelAttribute("FindPasswordQuestions")
    public FindPasswordQuestions[] findPasswordQuestions() {
        return FindPasswordQuestions.values();
    }

    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        model.addAttribute("signUpForm", new SignUpDTO());
        return "user/signup";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute SignUpDTO dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "/users/sign-up";
        }


    }

}
