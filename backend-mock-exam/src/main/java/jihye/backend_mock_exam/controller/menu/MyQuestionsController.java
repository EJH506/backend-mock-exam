package jihye.backend_mock_exam.controller.menu;

import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.domain.user.Role;
import jihye.backend_mock_exam.service.Page;
import jihye.backend_mock_exam.service.menu.myQuestions.MyQuestionsService;
import jihye.backend_mock_exam.service.menu.myQuestions.dto.MyQuestionSelectDeleteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/my-questions")
@RequiredArgsConstructor
public class MyQuestionsController {

    private final MyQuestionsService myQuestionsService;

    @GetMapping
    public String myQuestions(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("level", "전체");
        return "redirect:/my-questions/list";
    }

    @GetMapping("/list")
    public String myQuestionsList(@RequestAttribute("user") Role user,
                              @RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "level", required = false) String level,
                              @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
                              Model model) {

        // 선택한 주제에 존재하는 난이도 목록
        List<Integer> levels = myQuestionsService.levelListOfMyQuestion(user.getUserId());

        if (searchKeyword != null) {
            model.addAttribute("searchKeyword", searchKeyword);
        }
        if (level != null) {
            model.addAttribute("paramLevel", level);
        }

        Page<Question> questionList = myQuestionsService.myQuestionsList(user.getUserId(), level, searchKeyword, page);
        model.addAttribute("questionList", questionList);
        model.addAttribute("levels", levels);
        model.addAttribute("user", user);
        model.addAttribute("myQuestionSelectDeleteDto", new MyQuestionSelectDeleteDto());
        return "menu/myQuestions/my-questions-list";
    }
}
