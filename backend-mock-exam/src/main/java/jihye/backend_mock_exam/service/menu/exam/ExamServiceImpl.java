package jihye.backend_mock_exam.service.menu.exam;

import jihye.backend_mock_exam.domain.exam.*;
import jihye.backend_mock_exam.domain.history.ExamHistory;
import jihye.backend_mock_exam.domain.history.HistoryItemObject;
import jihye.backend_mock_exam.repository.menu.exam.ExamRepository;
import jihye.backend_mock_exam.service.menu.CommonService;
import jihye.backend_mock_exam.service.menu.QuestionFilter;
import jihye.backend_mock_exam.service.menu.exam.dto.SubmittedExamDto;
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
    private final CommonService commonService;

    // 주제 목록 조회
    @Override
    public List<Subject> findAllSubjects() {
        return commonService.findAllSubjects();
    }

    // 주제별 난이도 목록 조회
    @Override
    public List<Integer> levelListOfSubject(String subjectName) {

        return commonService.levelListOfSubject(subjectName);
    }

    // 주제,난이도별 문제 수 조회
    @Override
    public Long NumberOfSubject(String subjectName, String level) {
        QuestionFilter questionFilter = commonService.questionFilterConvert(subjectName, level);
        return examRepository.findNumberOfSubject(questionFilter.getSubjectId(), questionFilter.getLevelInt());
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

        return selectableNumbers;
    }

    // 주제,난이도,문항수에 해당하는 문제 목록 반환 (순서 랜덤)
    @Override
    public List<Long> shuffledQuestionList(String subjectName, String level, int number) {

        QuestionFilter questionFilter = commonService.questionFilterConvert(subjectName, level);
        List<Long> questionsId = examRepository.findShuffledQuestions(questionFilter.getSubjectId(), questionFilter.getLevelInt(), number);

        return questionsId;
    }

    // 시험 만들어 반환
    @Override
    public List<ExamItem> createExam(List<Long> questionsId) {

        List<Question> questions = commonService.findFilteredHistoryQuestions(questionsId);

        // 주제 이름까지 담아서 반환
        for (Question question : questions) {
            Subject subject = examRepository.findSubjectById(question.getSubjectId());
            question.setSubjectName(subject.getSubjectName());
        }

        ArrayList<ExamItem> exam = new ArrayList<>();

        for (Question question : questions) {
            ExamItem examItem = new ExamItem();
            examItem.setQuestion(question);

            List<Answer> answers = commonService.shuffledAnswerListByQuestion(question.getQuestionId());
            examItem.setAnswers(answers);

            exam.add(examItem);
        }

        log.info("service.ExamItemList={}", exam);

        return exam;
    }

    // 시험 히스토리 생성
    @Override
    public ExamHistory createExamHistory(SubmittedExamDto dto) {
        return commonService.createExamHistory(dto);
    }

    // 히스토리 상세 반환
    @Override
    public List<HistoryItemObject> createHistoryDetails(ExamHistory examHistory, String option) {
        return commonService.createHistoryDetails(examHistory, option);
    }

    // 히스토리 조회
    @Override
    public ExamHistory findExamHistoryById(Long historyId) {
        return commonService.findExamHistoryById(historyId);
    }

    // 히스토리에 속한 문제ID 조회
    @Override
    public List<Long> findQuestionsIdOfHistory(Long historyId, boolean isCorrect) {
        return commonService.findQuestionsIdOfHistory(historyId, isCorrect);
    }

    @Override
    // 조건에 맞는 보기 조회
    public List<Answer> findFilteredHistoryAnswers(List<Long> answersId) {
        return commonService.findFilteredHistoryAnswers(answersId);
    }

    // 문항 ID로 오답노트 저장 유무 조회
    @Override
    public boolean isSavedToIncorrectNote(Long userId, Long questionId) {
        return commonService.isSavedToIncorrectNote(userId, questionId);
    }

}
