package jihye.backend_mock_exam.service.menu.exam;

import jihye.backend_mock_exam.domain.exam.Subject;
import jihye.backend_mock_exam.repository.menu.exam.ExamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    // 주제 목록 조회
    @Override
    public List<Subject> findAllSubjects() {
        return examRepository.findAllSubjects();
    }

    // 주제 이름으로 주제 조회
    @Override
    public Subject findSubjectByName(String subjectName) {
        return examRepository.findSubjectByName(subjectName);
    }

    // 주제별 난이도 목록 조회
    @Override
    public List<Integer> levelListOfSubject(Long subjectId) {
        return examRepository.findLevelsBySubject(subjectId);
    }

    // 통합 문제 난이도 목록 조회
    @Override
    public List<Integer> allSubjectLevel() {
        return examRepository.findMinMaxLevel();
    }

    // 주제에 해당하는 문제 수 조회
    @Override
    public Long NumberOfSubject(String subjectName, int level) {
        Long subjectId = examRepository.findSubjectByName(subjectName).getSubjectId();
        return examRepository.findNumberOfSubject(subjectId, level);
    }

    // 출제 문항 수 목록 연산
    @Override
    public List<Integer> createQuestionNumberList(String subjectName, int level) {

        Long numberOfSubject = NumberOfSubject(subjectName, level);
        Integer questionUnit = examRepository.findQuestionUnitSetting();

        List<Integer> selectableNumbers = new ArrayList<>();

        for (int i=questionUnit; i<numberOfSubject; i += questionUnit) {
            selectableNumbers.add(i);
        }

        log.info("numberOfSubject={}", numberOfSubject);
        log.info("questionUnit={}", questionUnit);
        log.info("selectableNumbers={}", selectableNumbers);

        return selectableNumbers;
    }
}
