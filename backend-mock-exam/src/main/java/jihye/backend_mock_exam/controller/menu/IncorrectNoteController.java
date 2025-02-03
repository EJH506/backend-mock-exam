package jihye.backend_mock_exam.controller.menu;

import jihye.backend_mock_exam.service.Page;
import jihye.backend_mock_exam.domain.exam.Subject;
import jihye.backend_mock_exam.domain.incorrectNote.IncorrectItem;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.menu.incorrectNote.IncorrectNoteService;
import jihye.backend_mock_exam.service.menu.incorrectNote.dto.saveIncorrectAllDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Subject> subjects = incorrectNoteService.findAllSubjects();
        model.addAttribute("subjects", subjects);
        return "menu/incorrectNote/incorrectNote-subject";
    }

    @GetMapping("/list")
    public String list(@RequestAttribute("user") User user,
                       @RequestParam("subject") String subjectName,
                       @RequestParam(value = "level", required = false) String level,
                       @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
                       @RequestParam(value = "page", defaultValue = "1") int page,
                       Model model) {

        // 선택한 주제에 존재하는 난이도 목록
        List<Integer> levels = incorrectNoteService.levelListOfSubject(user.getUserId(), subjectName);

        // 오답노트 목록 반환
        Page<IncorrectItem> incorrectItemList = incorrectNoteService.incorrectList(user.getUserId(), subjectName, level, searchKeyword, page);

        if (searchKeyword != null) {
            model.addAttribute("searchKeyword", searchKeyword);
        }
        if (level != null) {
            model.addAttribute("paramLevel", level);
        }

        model.addAttribute("subject", subjectName);
        model.addAttribute("levels", levels);
        model.addAttribute("incorrectItemList", incorrectItemList);

        return "menu/incorrectNote/incorrectNote-list";
    }

    @PostMapping("/saveToggle")
    @ResponseBody
    public Map<String, Object> saveToggle(@RequestParam("userId") Long userId, @RequestParam("questionId") Long questionId, @RequestParam("isMyQuestion") boolean isMyQuestion, @RequestParam("isSaved") boolean isSaved) {

        Map<String, Object> response = new HashMap<>();

        try {
            // 저장 해제
            if (isSaved) {
                incorrectNoteService.unsaveIncorrectNote(userId, questionId, isMyQuestion);
                
            // 저장
            } else {
                incorrectNoteService.saveIncorrectNote(userId, questionId, isMyQuestion);
            }
            response.put("success", true);

        } catch (Exception e) {
            response.put("success", false);
            e.printStackTrace();
        }

        return response;
    }

    @PostMapping("/saveIncorrectAll")
    @ResponseBody
    public Map<String, List<Long>> saveIncorrectAll(@RequestBody saveIncorrectAllDto dto) {

        List<Long> savedQuestionsId = incorrectNoteService.saveIncorrectAll(dto.getUserId(), dto.getWrongQuestions());

        Map<String, List<Long>> savedQuestionJson = new HashMap<>();
        savedQuestionJson.put("savedQuestionId", savedQuestionsId);

        return savedQuestionJson;
    }
}