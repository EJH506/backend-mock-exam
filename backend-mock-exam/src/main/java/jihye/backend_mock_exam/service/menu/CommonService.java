package jihye.backend_mock_exam.service.menu;

import jihye.backend_mock_exam.domain.Questions;
import jihye.backend_mock_exam.domain.exam.*;
import jihye.backend_mock_exam.domain.history.ExamHistory;
import jihye.backend_mock_exam.domain.history.HistoryItemObject;
import jihye.backend_mock_exam.domain.myQuestion.MyQuestion;
import jihye.backend_mock_exam.service.menu.exam.dto.SubmittedExamDto;

import java.util.List;

public interface CommonService {

    // 주제 목록 조회
    List<Subject> findAllSubjects();

    // 주제별 난이도 목록 조회
    List<Integer> levelListOfSubject(Long userId, String subjectName);

    // 주제별 난이도 목록 조회 (문항이 존재하는 것만 조회)
    List<Integer> levelListOfSubjectWithItem(Long userId, String subjectName);

    // 조건에 맞는 문항 조회
    List<Question> findFilteredHistoryQuestions(List<Long> questionsId, List<Boolean> isMyQuestion);

    // 문제의 보기 조회 (순서 랜덤)
    List<Answer> shuffledAnswerListByQuestion(Questions question, boolean isMyQuestion);

    // 주제 이름으로 주제 조회
    Subject findSubjectByName(String subjectName);

    // 시험 히스토리 생성
    ExamHistory createExamHistory(SubmittedExamDto dto);

    // 히스토리 상세 반환
    List<HistoryItemObject> createHistoryDetails(ExamHistory examHistory, String option);

    // 히스토리 조회
    ExamHistory findExamHistoryById(Long historyId);

    // 히스토리에 속한 문제 조회 (전체를 찾으려면 true, 틀린문제만이면 false)
    List<Questions> findQuestionsIdOfHistory(Long historyId, boolean isCorrect);

    // 조건에 맞는 보기 조회
    List<Answer> findFilteredHistoryAnswers(List<Long> answersId, List<Boolean> isMyQuestion);

    // 문항 ID로 오답노트 저장 유무 조회
    boolean isSavedToIncorrectNote(Long userId, Long questionId, boolean isMyQuestion);

    // 매개변수로 사용될 subject와 level의 값을 통합인지 아닌지에 따라 적절히 변환
    QuestionFilter questionFilterConvert(String subjectName, String level);
    QuestionFilter questionFilterConvert(String level);

    // 나만의 문제에 존재하는 레벨 목록 보기 (문항이 존재하는 것만 조회)
    List<Integer> levelListOfMyQuestionWithItem(Long userId);

    // 사용자의 나만의 문제 문항 수 조회
    Integer findNumberOfMyQuestion(Long userId, int level);

    // 나만의 문제의 난이도, 문항수에 해당하는 문제 목록 조회
    List<MyQuestion> findShuffledMyQuestions(Long userId, int level, int number);
}
