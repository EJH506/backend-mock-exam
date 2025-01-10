package jihye.backend_mock_exam.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-questions")
public class MyQuestionsController {

    @GetMapping
    public String myQuestions() {
        return "menu/my-questions/my-questions-list";
    }
}
