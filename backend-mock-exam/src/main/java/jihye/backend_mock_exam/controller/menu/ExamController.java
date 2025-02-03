package jihye.backend_mock_exam.controller.menu;

import jakarta.servlet.http.HttpServletRequest;
import jihye.backend_mock_exam.domain.Questions;
import jihye.backend_mock_exam.domain.exam.*;
import jihye.backend_mock_exam.domain.history.ExamHistory;
import jihye.backend_mock_exam.domain.history.HistoryItemObject;
import jihye.backend_mock_exam.domain.user.Role;
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
    public String subject(@RequestAttribute("user") Role user, Model model) {

        // 주제 목록 (문항이 존재하는 것만 조회)
        List<Subject> subjects = examService.findAllSubjectsWithItem(user.getUserId());
        model.addAttribute("subjects", subjects);
        return "menu/exam/exam-subject";
    }

    @GetMapping("/level")
    public String level(@RequestAttribute("user") Role user, @RequestParam("subject") String subjectName, Model model) {

        if (subjectName == null) { return "redirect:/exam/subject"; }

        // 난이도 목록
        List<Integer> levels = examService.levelListOfSubjectWithItem(user.getUserId(), subjectName);

        model.addAttribute("levels", levels);
        model.addAttribute("subject", subjectName);

        return "menu/exam/exam-level";
    }

    @GetMapping("/number")
    public String number(@RequestAttribute("user") Role user,
                         @RequestParam("subject") String subjectName,
                         @RequestParam("level") String level,
                         Model model) {

        if (subjectName == null || level == null) { return "redirect:/exam/subject"; }

        // 문항 수 목록
        Integer numberOfSubject = examService.NumberOfSubject(user.getUserId(), subjectName, level);
        List<Integer> selectableNumbers = examService.createQuestionNumberList(user.getUserId(), subjectName, level);

        model.addAttribute("numberOfSubject", numberOfSubject);
        model.addAttribute("selectableNumbers", selectableNumbers);
        model.addAttribute("subject", subjectName);
        model.addAttribute("level", level);

        return "menu/exam/exam-number";
    }

    @GetMapping("/take-exam")
    public String takeExamPage(@RequestAttribute("user") Role user,
                               @RequestParam(value = "historyId", required = false) Long historyId,
                               @RequestParam(value = "subject", required = false) String subjectName,
                               @RequestParam(value = "level", required = false) String level,
                               @RequestParam(value = "number", required = false) Integer number,
                               Model model) {

        List<? extends Questions> questions = null;

        // 틀린문제만 재도전이 아닐경우
        if (historyId == null) {
            if (subjectName == null || level == null || number == null) { return "redirect:/exam/subject"; }
            questions = examService.shuffledQuestionList(user.getUserId(), subjectName, level, number);
            for (Questions question : questions) {
                log.info("일반문제question={}", question);
            }
        }

        // 틀린문제만 재도전일 경우
        if (historyId != null) {
            questions = examService.findQuestionsIdOfHistory(historyId, false);
            for (Questions question : questions) {
                log.info("틀린문제question={}", question);
            }
        }

        // 시험 문항 생성
        List<ExamItem> examItems = examService.createExam(questions);
        number = examItems.size();

        model.addAttribute("exam", new Exam(subjectName, level, number, examItems));
        model.addAttribute("submittedExamDto", new SubmittedExamDto());

        return "menu/exam/take-exam";
    }

    @PostMapping("/take-exam")
    public String takeExam(@ModelAttribute SubmittedExamDto dto, HttpServletRequest request) {

        log.info("dto={}", dto);
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
