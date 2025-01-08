package jihye.backend_mock_exam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class HomeController {

    @ModelAttribute("menus")
    public Map<String, String> menus() {
        Map<String, String> menus = new HashMap<>();
        menus.put("exam", "문제풀기");
        menus.put("incorrectNote", "오답노트");
        menus.put("history", "히스토리");
        menus.put("memo", "메모장");
        menus.put("myQuestions", "나만의 문제 등록");
        return menus;
    }

    @GetMapping("/")
    public String home(Model model) {

        // 스프링 시큐리티 인증정보
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication={}", authentication);

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "welcome";
        }

        UserDetails loginUser = (UserDetails) authentication.getPrincipal();
        model.addAttribute("loginUser", loginUser);

        return "home";
    }
}
