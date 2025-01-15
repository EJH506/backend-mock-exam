package jihye.backend_mock_exam.service.menu.incorrectNote;

import jihye.backend_mock_exam.domain.exam.Subject;
import jihye.backend_mock_exam.service.menu.exam.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IncorrectNoteService {

    private final ExamService examService;

    // 주제 목록 조회
    public List<Subject> findAllSubjects() {
        return examService.findAllSubjects();
    }

    // 주제 목록을 이름만 반환
    public List<String> subjectNames(List<Subject> subjects) {
        return examService.subjectNames(subjects);
    }
}
