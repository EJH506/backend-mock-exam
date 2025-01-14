package jihye.backend_mock_exam.service.menu.exam;

import jihye.backend_mock_exam.domain.exam.*;
import jihye.backend_mock_exam.service.menu.exam.dto.SubmittedExamDto;

import java.util.List;

public interface ExamService {

    // 주제 목록 조회
    List<Subject> findAllSubjects();

    // 주제 목록을 이름만 반환
    List<String> subjectNames(List<Subject> subjects);
    
    // 주제 이름으로 주제 조회
    Subject findSubjectByName(String subjectName);
    
    // 주제별 난이도 목록 조회
    List<Integer> levelListOfSubject(String subjectName);

    // 주제,난이도별 문제 수 조회
    Long NumberOfSubject(String subjectName, String level);

    // 출제 문항 수 목록 연산
    List<Integer> createQuestionNumberList(String subjectName, String level);
    
    // 주제,난이도,문항수에 해당하는 문제 목록 조회 (순서 랜덤)
    List<Question> shuffledQuestionList(String subjectName, String level, int number);

    // 문제의 보기 조회 (순서 랜덤)
    List<Answer> shuffledAnswerListByQuestion(Long questionId);

    // 시험 만들어 반환
    List<QuestionItem> createExam(List<Question> questions);

    // 시험 히스토리 생성
    ExamHistory createExamHistory(SubmittedExamDto dto);

    // 히스토리 상세 반환
    List<HistoryItem> createHistoryDetails(List<Long> questionsId, List<Long> correctAnswersId, List<Long> userAnswersId);
}
