package jihye.backend_mock_exam.controller.menu;

import jakarta.servlet.http.HttpServletRequest;
import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.domain.user.Role;
import jihye.backend_mock_exam.service.Page;
import jihye.backend_mock_exam.service.menu.memo.dto.MemoSelectDeleteDto;
import jihye.backend_mock_exam.service.menu.myQuestions.MyQuestionsService;
import jihye.backend_mock_exam.service.menu.myQuestions.dto.MyQuestionSelectDeleteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
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

    @PostMapping("/delete")
    public String selectedMemoDelete(@ModelAttribute MyQuestionSelectDeleteDto dto,
                                     @RequestParam("level") String level,
                                     RedirectAttributes redirectAttributes) {

        myQuestionsService.myQuestionSelectDelete(dto);

        log.info("MyQuestionSelectDeleteDto={}", dto);
        redirectAttributes.addAttribute("level", level);
        return "redirect:/my-questions/list";
    }

    @GetMapping("/{questionId}")
    public String myQuestionDetail(@PathVariable("questionId") Long questionId) {
//        myQuestionsService.findMyQuestionById(questionId);
        return null;
    }
}
