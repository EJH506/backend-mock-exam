package jihye.backend_mock_exam.controller.admin;

import jihye.backend_mock_exam.domain.exam.Subject;
import jihye.backend_mock_exam.domain.user.Role;
import jihye.backend_mock_exam.service.admin.AdminService;
import jihye.backend_mock_exam.service.admin.AdminSubjectEditDto;
import jihye.backend_mock_exam.service.menu.CommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminMenus adminMenus;
    private final AdminService adminService;
    private final CommonService commonService;

    @ModelAttribute("themeColor")
    public String themeColor() {
        return "#544D4D";
    }

    @ModelAttribute("adminMenus")
    public Map<String, String> adminMenus() {
        return adminMenus.adminMenus();
    }

    @GetMapping
    public String admin() {
        return "admin/admin-menu";
    }

    @GetMapping("/exam")
    public String exam(Model model) {

        // 주제 목록 (항목이 없더라도 전체 목록 조회)
        List<Subject> subjects = adminService.findAllSubjects();
        model.addAttribute("subjects", subjects);

        return "admin/admin-exam";
    }

    @GetMapping("/edit")
    public String subject(@RequestAttribute("user") Role user, @RequestParam("subject") String subjectName, Model model) {

        // 주제 정보 (이름, 난이도 목록)
        Subject subjectInfo = commonService.findSubjectByName(subjectName);
        List<Integer> levels = commonService.levelListOfSubject(user.getUserId(), String.valueOf(subjectInfo.getSubjectId()));

        model.addAttribute("subject", new Subject(subjectInfo.getSubjectId(), subjectName, levels));
        model.addAttribute("adminSubjectEditDto", new AdminSubjectEditDto(subjectName));

        return "admin/admin-subject-edit";
    }

}
