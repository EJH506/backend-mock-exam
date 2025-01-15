package jihye.backend_mock_exam.controller.menu;

import jihye.backend_mock_exam.service.menu.incorrectNote.IncorrectNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/incorrect-note")
@RequiredArgsConstructor
public class IncorrectNoteController {

    private final IncorrectNoteService incorrectNoteService;

    @GetMapping
    public String incorrectNoteHome() {
        return "redirect:/incorrect-note/subject";
    }

    @GetMapping("/subject")
    public String subject(Model model) {
        // 주제 목록
        List<String> subjectNames = incorrectNoteService.subjectNames(incorrectNoteService.findAllSubjects());
        model.addAttribute("subjects", subjectNames);
        return "menu/incorrectNote/incorrectNote-subject";
    }

    @GetMapping("/list")
    public String list(@RequestParam("subject") String subject, @RequestParam(value = "level", required = false) String level, Model model) {

        // 선택한 주제에 존재하는 난이도 목록
//        List<Integer> levels = incorrectNoteService.levelListOfSubject(subject);

        // 주제, 난이도 선택에 따른 오답노트 목록
//        incorrectNoteService.incorrectList();

//
//        model.addAttribute("subject", subject);
//        if (level != null) {
//            model.addAttribute("level", level);
//        }
//        model.addAttribute("levels", levels);
//        model.addAttribute("incorrectNoteSearchDto", new IncorrectNoteSearchDto());

        return "menu/incorrectNote/incorrectNote-list";
    }

}
