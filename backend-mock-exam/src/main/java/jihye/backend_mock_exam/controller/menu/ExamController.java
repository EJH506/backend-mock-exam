package jihye.backend_mock_exam.controller.menu;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jihye.backend_mock_exam.domain.exam.*;
import jihye.backend_mock_exam.domain.history.ExamHistory;
import jihye.backend_mock_exam.domain.history.HistoryItemObject;
import jihye.backend_mock_exam.service.menu.exam.ExamService;
import jihye.backend_mock_exam.service.menu.exam.dto.SubmittedExamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String level(@RequestParam("subject") String subjectName, Model model) {

        if (subjectName == null) { return "redirect:/exam/subject"; }

        // 난이도 목록
        List<Integer> levels = examService.levelListOfSubject(subjectName);

        model.addAttribute("levels", levels);
        model.addAttribute("subject", subjectName);

        return "menu/exam/exam-level";
    }

    @GetMapping("/number")
    public String number(@RequestParam("subject") String subjectName,
                         @RequestParam("level") String level,
                         Model model) {

        if (subjectName == null || level == null) { return "redirect:/exam/subject"; }

        // 문항 수 목록
        Long numberOfSubject = examService.NumberOfSubject(subjectName, level);
        List<Integer> selectableNumbers = examService.createQuestionNumberList(subjectName, level);

        model.addAttribute("numberOfSubject", numberOfSubject);
        model.addAttribute("selectableNumbers", selectableNumbers);
        model.addAttribute("subject", subjectName);
        model.addAttribute("level", level);

        return "menu/exam/exam-number";
    }

    @GetMapping("/take-exam")
    public String takeExamPage(@RequestParam(value = "historyId", required = false) Long historyId,
                               @RequestParam(value = "subject", required = false) String subjectName,
                               @RequestParam(value = "level", required = false) String level,
                               @RequestParam(value = "number", required = false) Integer number,
                               Model model) {

        List<Long> questionsId = null;

        // 틀린문제만 재도전이 아닐경우
        if (historyId == null) {
            if (subjectName == null || level == null || number == null) { return "redirect:/exam/subject"; }
            questionsId = examService.shuffledQuestionList(subjectName, level, number);
        }

        // 틀린문제만 재도전일 경우
        if (historyId != null) {
            questionsId = examService.findQuestionsIdOfHistory(historyId, false);
        }

        // 시험 문항 생성
        List<ExamItem> examItems = examService.createExam(questionsId);
        number = examItems.size();

        model.addAttribute("exam", new Exam(subjectName, level, number, examItems));
        model.addAttribute("submittedExamDto", new SubmittedExamDto());

        return "menu/exam/take-exam";
    }

    @PostMapping("/take-exam")
    public String takeExam(@ModelAttribute SubmittedExamDto dto, HttpServletRequest request) {

        // 시험 히스토리 생성
        ExamHistory examHistory = examService.createExamHistory(dto);
        examHistory.setCorrectRate(Math.round(examHistory.getCorrectRate() * 10) / 10.0);
        request.getSession().setAttribute("examHistory", examHistory);
//        redirectAttributes.addFlashAttribute("examHistory", examHistory);
        return "redirect:/exam/result";
    }

    @GetMapping("/result")
    public String examResultPage(@RequestParam(value = "option", defaultValue = "all") String option,
//                                 @ModelAttribute("examHistory") ExamHistory examHistory,
                                 HttpServletRequest request,
                                 Model model) {

        // Ajax 요청인지 확인
        boolean isAjaxRequest = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        // 히스토리 정보에 해당하는 문항과 답변 기록 추출
        ExamHistory examHistory = (ExamHistory) request.getSession(false).getAttribute("examHistory");
        List<HistoryItemObject> historyDetails = examService.createHistoryDetails(examHistory, option);

        model.addAttribute("examHistory", examHistory);
        model.addAttribute("historyDetails", historyDetails);

        if (isAjaxRequest) {
            return "menu/exam/exam-result :: viewQuestionsArea";
        } else {
            return "menu/exam/exam-result";
        }
    }

}
