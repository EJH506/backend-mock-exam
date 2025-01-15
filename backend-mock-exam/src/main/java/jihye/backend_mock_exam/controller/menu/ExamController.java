package jihye.backend_mock_exam.controller.menu;

import jihye.backend_mock_exam.domain.exam.*;
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

        // 주제 목록
        List<Subject> subjects = examService.findAllSubjects();
        model.addAttribute("subjects", subjects);

        return "menu/exam/exam-subject";
    }

    @GetMapping("/level")
    public String level(@RequestParam("subject") Long subjectId, Model model) {

        if (subjectId == null) { return "redirect:/exam/subject"; }

        // 난이도 목록
        List<Integer> levels = examService.levelListOfSubject(subjectId);
        Subject subject = examService.findSubjectById(subjectId);

        model.addAttribute("levels", levels);
        model.addAttribute("subject", subject);

        return "menu/exam/exam-level";
    }

    @GetMapping("/number")
    public String number(@RequestParam("subject") Long subjectId,
                         @RequestParam("level") Integer level,
                         Model model) {

        if (subjectId == null || level == null) { return "redirect:/exam/subject"; }

        // 문항 수 목록
        Long numberOfSubject = examService.NumberOfSubject(subjectId, level);
        List<Integer> selectableNumbers = examService.createQuestionNumberList(subjectId, level);

        Subject subject = examService.findSubjectById(subjectId);

        model.addAttribute("numberOfSubject", numberOfSubject);
        model.addAttribute("selectableNumbers", selectableNumbers);
        model.addAttribute("subject", subject);
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("level", level);

        return "menu/exam/exam-number";
    }

    @GetMapping("/take-exam")
    public String takeExamPage(@RequestParam(value = "historyId", required = false) Long historyId,
                               @RequestParam(value = "subject", required = false) Long subjectId,
                               @RequestParam(value = "level", required = false) Integer level,
                               @RequestParam(value = "number", required = false) Integer number,
                               Model model) {

        List<Long> questionsId = null;

        // 틀린문제만 재도전이 아닐경우
        if (historyId == null) {
            if (subjectId == null || level == null || number == null) { return "redirect:/exam/subject"; }
            questionsId = examService.shuffledQuestionList(subjectId, level, number);
        }

        // 틀린문제만 재도전일 경우
        if (historyId != null) {
//            ExamHistory history = examService.findExamHistoryById(historyId);
            questionsId = examService.findQuestionsIdOfHistory(historyId, false);
        }

        // 시험 문항 생성
        List<ExamItem> examItems = examService.createExam(questionsId);
        number = examItems.size();

        Subject subject = examService.findSubjectById(subjectId);

        model.addAttribute("exam", new Exam(subjectId, level, number, examItems));
        model.addAttribute("submittedExamDto", new SubmittedExamDto());
        model.addAttribute("subject", subject);

        return "menu/exam/take-exam";
    }

    @PostMapping("/take-exam")
    public String takeExam(@ModelAttribute SubmittedExamDto dto, RedirectAttributes redirectAttributes) {

        // 시험 히스토리 생성
        ExamHistory examHistory = examService.createExamHistory(dto);
        redirectAttributes.addFlashAttribute("examHistory", examHistory);
        return "redirect:/exam/result";
    }

    @GetMapping("/result")
    public String examResultPage(@ModelAttribute("examHistory") ExamHistory examHistory, Model model) {
        log.info("examHistory={}",examHistory);

        // 히스토리 정보에 해당하는 문항과 답변 기록 추출
        List<HistoryItemObject> historyDetails = examService.createHistoryDetails(examHistory);
        model.addAttribute("historyDetails", historyDetails);
        return "menu/exam/exam-result";
    }

}
