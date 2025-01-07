package jihye.backend_mock_exam.controller.exam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @GetMapping("/")
    public String subject() {
        return "exam/exam-subject";
    }
}
