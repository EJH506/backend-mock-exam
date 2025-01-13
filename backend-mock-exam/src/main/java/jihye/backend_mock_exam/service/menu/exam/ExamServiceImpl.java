package jihye.backend_mock_exam.service.menu.exam;

import jihye.backend_mock_exam.controller.menu.ExamConst;
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

    // 주제 목록을 이름만 반환
    @Override
    public List<String> subjectNames(List<Subject> subjects) {

        List<String> subjectNames = new ArrayList<>();
        for (Subject subject : subjects) {
            subjectNames.add(subject.getSubjectName());
        }
        return subjectNames;
    }

    // 주제 이름으로 주제 조회
    @Override
    public Subject findSubjectByName(String subjectName) {
        return examRepository.findSubjectByName(subjectName);
    }

    // 주제별 난이도 목록 조회
    @Override
    public List<Integer> levelListOfSubject(String subjectName) {

        // 통합 문제가 아닐 시
        if (!(ExamConst.SUBJECT_ALL.equals(subjectName))) {
            Long subjectId = findSubjectByName(subjectName).getSubjectId();
            return examRepository.findLevelsBySubject(subjectId);
            
        // 통합 문제일 시
        } else {
            return examRepository.findMinMaxLevel();
        }
    }


    // 주제별 문제 수 조회
    @Override
    public Long NumberOfSubject(String subjectName, String level) {

        Long subjectId = 0L;
        int levelInt = 0;

        // 통합 문제가 아닐 시
        if (!(ExamConst.SUBJECT_ALL.equals(subjectName))) {
            subjectId = examRepository.findSubjectByName(subjectName).getSubjectId();
        }

        // 전체 레벨이 아닐 시
        if (!(ExamConst.LEVEL_ALL.equals(level))) {
            levelInt = Integer.parseInt(level);
        }
        return examRepository.findNumberOfSubject(subjectId, levelInt);
    }

    // 출제 문항 수 목록 연산
    @Override
    public List<Integer> createQuestionNumberList(String subjectName, String level) {

        Long numberOfSubject = NumberOfSubject(subjectName, level); // 과목별 총 문항 수
        Integer questionUnit = examRepository.findQuestionUnitSetting(); // 관리자에 의해 설정된 분류 단위

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
