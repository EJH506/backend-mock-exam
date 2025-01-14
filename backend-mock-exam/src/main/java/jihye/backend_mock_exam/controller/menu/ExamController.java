package jihye.backend_mock_exam.controller.menu;

import jihye.backend_mock_exam.domain.exam.ExamHistory;
import jihye.backend_mock_exam.domain.exam.HistoryItem;
import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.domain.exam.QuestionItem;
import jihye.backend_mock_exam.service.menu.exam.ExamService;
import jihye.backend_mock_exam.service.menu.exam.dto.SubmittedExamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping
    public String examHome() {
        return "redirect:/exam/subject";
    }

    @GetMapping("/subject")
    public String subject(Model model) {

        List<String> subjectNames = examService.subjectNames(examService.findAllSubjects());

        model.addAttribute("subjects", subjectNames);

        return "menu/exam/exam-subject";
    }

    @GetMapping("/level")
    public String level(@RequestParam("subject") String subjectName, Model model) {

        if (subjectName == null) {
            return "redirect:/exam/subject";
        }

        List<Integer> levels = examService.levelListOfSubject(subjectName);

        model.addAttribute("levels", levels);
        model.addAttribute("subject", subjectName);

        return "menu/exam/exam-level";
    }

    @GetMapping("/number")
    public String number(@RequestParam("subject") String subjectName,
                         @RequestParam("level") String level,
                         Model model) {

        if (level == null) {
            return "redirect:/exam/subject";
        }

        Long numberOfSubject = examService.NumberOfSubject(subjectName, level);
        List<Integer> selectableNumbers = examService.createQuestionNumberList(subjectName, level);

        model.addAttribute("numberOfSubject", numberOfSubject);
        model.addAttribute("selectableNumbers", selectableNumbers);
        model.addAttribute("subject", subjectName);
        model.addAttribute("level", level);

        return "menu/exam/exam-number";
    }

    @GetMapping("/take-exam")
    public String takeExamPage(@RequestParam("subject") String subjectName,
                           @RequestParam("level") String level,
                           @RequestParam("number") int number,
                           Model model) {

        List<Question> questions = examService.shuffledQuestionList(subjectName, level, number);
        List<QuestionItem> QuestionItems = examService.createExam(questions);

        model.addAttribute("subject", subjectName);
        model.addAttribute("level", level);
        model.addAttribute("totalQuestionsCount", number);
        model.addAttribute("questionItems", QuestionItems);
        model.addAttribute("submittedExamDto", new SubmittedExamDto());

        return "menu/exam/take-exam";
    }

    @PostMapping("/take-exam")
    public String takeExam(@ModelAttribute SubmittedExamDto dto, RedirectAttributes redirectAttributes) {

//        if (bindingResult.hasErrors()) {
//            log.info("errors={}", bindingResult);
//            return "menu/exam/take-exam";
//        }

        ExamHistory examHistory = examService.createExamHistory(dto);
        redirectAttributes.addFlashAttribute("examHistory", examHistory);
        return "redirect:/exam/result";
    }

    @GetMapping("/result")
    public String examResultPage(@ModelAttribute ExamHistory examHistory, Model model) {

        List<Long> questionsId = examHistory.getQuestions();
        List<Long> correctAnswersId = examHistory.getCorrectAnswers();
        List<Long> userAnswersId = examHistory.getUserAnswers();

        List<HistoryItem> historyDetails = examService.createHistoryDetails(questionsId, correctAnswersId, userAnswersId);

        model.addAttribute("historyDetails", historyDetails);
        log.info("historyDetails={}", model.getAttribute("historyDetails"));

        return "menu/exam/exam-result";
    }
}
