package jihye.backend_mock_exam.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @GetMapping
    public String examHome() {
        return "redirect:/exam/subject";
    }

    @GetMapping("/subject")
    public String subject() {
        return "menu/exam/exam-subject";
    }

    @GetMapping("/level")
    public String level() {
        return "menu/exam/exam-level";
    }

    @GetMapping("/choose-number")
    public String chooseNumber() {
        return "menu/exam/exam-choose-number";
    }
}
