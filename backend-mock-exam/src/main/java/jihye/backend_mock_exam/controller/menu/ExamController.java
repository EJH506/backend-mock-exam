package jihye.backend_mock_exam.controller.menu;

import jihye.backend_mock_exam.domain.exam.Subject;
import jihye.backend_mock_exam.service.menu.exam.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

        List<String> subjectNames = new ArrayList<>();
        for (Subject subject : examService.findAllSubjects()) {
            subjectNames.add(subject.getSubjectName());
        }

        model.addAttribute("subjects", subjectNames);

        return "menu/exam/exam-subject";
    }

    @GetMapping("/level")
    public String level(@RequestParam("subject") String subjectName, Model model) {

        if (subjectName == null) {
            return "redirect:/exam/subject";
        }

        List<Integer> levels = null;

        if ("all".equals(subjectName)) {
            levels = examService.allSubjectLevel();

        } else {
            Subject subject = examService.findSubjectByName(subjectName);
            Long subjectId = subject.getSubjectId();

            levels = examService.levelListOfSubject(subjectId);
        }

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

        int levelInt = 0;
        if (!("all".equals(level))) {
            try {
                levelInt = Integer.parseInt(level);
            } catch (NumberFormatException e) {
                return "redirect:/exam/subject";
            }
        }

        Long numberOfSubject = examService.NumberOfSubject(subjectName, levelInt);
        List<Integer> selectableNumbers = examService.createQuestionNumberList(subjectName, levelInt);
        model.addAttribute("numberOfSubject", numberOfSubject);
        model.addAttribute("selectableNumbers", selectableNumbers);
        model.addAttribute("subject", subjectName);
        model.addAttribute("level", level);

        return "menu/exam/exam-number";
    }

    @GetMapping("/take-exam")
    public String takeExam() {
        return "menu/exam/take-exam";
    }
}
