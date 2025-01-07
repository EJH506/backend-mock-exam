package jihye.backend_mock_exam.controller;

import jihye.backend_mock_exam.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashMap;
import java.util.Map;

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
    public String home(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model) {

        if (loginUser == null) {
            return "welcome";
        }

        model.addAttribute("loginUser", loginUser);
        return "home";
    }
}
