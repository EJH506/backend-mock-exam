package jihye.backend_mock_exam.service.admin;

import jihye.backend_mock_exam.domain.exam.Subject;
import jihye.backend_mock_exam.repository.admin.AdminRepository;
import jihye.backend_mock_exam.service.menu.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final CommonService commonService;

    // 주제 목록 조회 (항목이 없더라도 전체 목록 조회)
    public List<Subject> findAllSubjects() {
        return commonService.findAllSubjects();
    }

    // 주제별 난이도 목록 조회 (문항이 없어도 전체 조회)
    public List<Integer> levelListOfSubject(Long userId, String subjectName) {
        return commonService.levelListOfSubject(userId, subjectName);
    }
}
