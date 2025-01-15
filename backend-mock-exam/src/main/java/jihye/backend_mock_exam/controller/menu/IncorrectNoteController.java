package jihye.backend_mock_exam.controller.menu;

import jihye.backend_mock_exam.service.menu.incorrectNote.IncorrectNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    public String level() {
        return "menu/incorrectNote/incorrectNote-list";
    }

}
