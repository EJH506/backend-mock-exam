package jihye.backend_mock_exam.controller.admin;

import jihye.backend_mock_exam.domain.exam.Subject;
import jihye.backend_mock_exam.domain.user.Role;
import jihye.backend_mock_exam.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
//
//    private final AdminService adminService;
//
//    @GetMapping("/subject")
//    public String subject(Model model) {
//
//        // 주제 목록 (문항이 존재하는 것만 조회)
//        List<Subject> subjects = adminService.findAllSubjects();
//        model.addAttribute("subjects", subjects);
//
//        return "menu/admin/admin-subject";
//    }
//
//    @GetMapping("/level")
//    public String level(@RequestAttribute("user") Role user, @RequestParam("subject") String subjectName, Model model) {
//
//        if (subjectName == null) { return "redirect:/exam/subject"; }
//
//        // 난이도 목록
//        List<Integer> levels = adminService.levelListOfSubject(user.getUserId(), subjectName);
//
//        model.addAttribute("levels", levels);
//        model.addAttribute("subject", subjectName);
//
//        return "menu/exam/exam-level";
//    }

}
