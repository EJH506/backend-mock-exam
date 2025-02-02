package jihye.backend_mock_exam.service.menu.exam;

import jihye.backend_mock_exam.controller.menu.ExamConst;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final CommonService commonService;

    // 주제 목록 조회 (문항이 존재하는 것만 조회)
    public List<Subject> findAllSubjectsWithItem(Long userId) {
        List<Subject> allSubjectsWithItem = examRepository.findAllSubjectsWithItem();
        if (commonService.levelListOfMyQuestionWithItem(userId).size() > 0) {
            allSubjectsWithItem.add(new Subject(null, ExamConst.SUBJECT_MYQUESTIONS));
        }
        return allSubjectsWithItem;
    }

    // 주제별 난이도 목록 조회 (문항이 존재하는 것만 조회)
    public List<Integer> levelListOfSubjectWithItem(Long userId, String subjectName) {
        return commonService.levelListOfSubjectWithItem(userId, subjectName);
    }

    // 주제,난이도별 문제 수 조회
    public Integer NumberOfSubject(Long userId, String subjectName, String level) {

        QuestionFilter questionFilter = commonService.questionFilterConvert(subjectName, level);

        // 나만의 문제일 시
        if (ExamConst.SUBJECT_MYQUESTIONS.equals(subjectName)) {
            return commonService.findNumberOfMyQuestion(userId, questionFilter.getLevelInt());
        // 아닐 시
        } else {

            // 통합 문제일 시
            if (ExamConst.SUBJECT_ALL.equals(subjectName)) {
                return examRepository.findNumberOfSubjectAll(questionFilter.getLevelInt(), userId);
            } else {
                return examRepository.findNumberOfSubject(questionFilter.getSubjectId(), questionFilter.getLevelInt());
            }

        }
    }

    // 출제 문항 수 목록 연산
    @Transactional
    public List<Integer> createQuestionNumberList(Long userId, String subjectName, String level) {

        Integer numberOfSubject = NumberOfSubject(userId, subjectName, level); // 과목별 총 문항 수
        Integer questionUnit = examRepository.findQuestionUnitSetting(); // 관리자에 의해 설정된 분류 단위

        List<Integer> selectableNumbers = new ArrayList<>();
        for (int i=questionUnit; i<numberOfSubject; i += questionUnit) {
            selectableNumbers.add(i);
        }

        return selectableNumbers;
    }

    // 주제,난이도,문항수에 해당하는 문제 목록 반환 (순서 랜덤)
    public List<Question> shuffledQuestionList(Long userId, String subjectName, String level, int number) {

        QuestionFilter questionFilter = commonService.questionFilterConvert(subjectName, level);
        List<Question> questions;

        // 나만의 문제일 시
        if (ExamConst.SUBJECT_MYQUESTIONS.equals(subjectName)) {
            log.info("나만의 문제일시");
//            questions = commonService.findShuffledMyQuestions(userId, questionFilter.getLevelInt(), number);
                questions = null;
            // 아닐 시
        } else {
            log.info("나만의 문제 아닐시");
            // 통합 문제일 시
            if (ExamConst.SUBJECT_ALL.equals(subjectName)) {
                log.info("통합 문제일 시");
                questions = examRepository.findShuffledAllQuestions(questionFilter.getLevelInt(), number, userId);
            } else {
                log.info("통합 문제 아닐시");
                questions = examRepository.findShuffledQuestions(questionFilter.getSubjectId(), questionFilter.getLevelInt(), number);
            }
        }

        log.info("questions={}", questions);
        return questions;
    }

    // 시험 만들어 반환
    @Transactional
    public List<ExamItem> createExam(List<Question> questions, boolean isMyQuestion) {

        ArrayList<ExamItem> exam = new ArrayList<>();
//        List<Question> questions = commonService.findFilteredHistoryQuestions(questionsId, isMyQuestion);

        for (Question question : questions) {
            ExamItem examItem = new ExamItem();
            examItem.setQuestion(question);

            List<Answer> answers = commonService.shuffledAnswerListByQuestion(question.getQuestionId(), isMyQuestion);
            examItem.setAnswers(answers);

            exam.add(examItem);
        }

        return exam;
    }

    // 시험 히스토리 생성
    public ExamHistory createExamHistory(SubmittedExamDto dto) {
        return commonService.createExamHistory(dto);
    }

    // 히스토리 상세 반환
    public List<HistoryItemObject> createHistoryDetails(ExamHistory examHistory, String option) {
        return commonService.createHistoryDetails(examHistory, option);
    }

    // 히스토리 조회
    public ExamHistory findExamHistoryById(Long historyId) {
        return commonService.findExamHistoryById(historyId);
    }

    // 히스토리에 속한 문제ID 조회
    public List<Long> findQuestionsIdOfHistory(Long historyId, boolean isCorrect) {
        return commonService.findQuestionsIdOfHistory(historyId, isCorrect);
    }

    // 조건에 맞는 보기 조회
    public List<Answer> findFilteredHistoryAnswers(List<Long> answersId, boolean isMyQuestion) {
        return commonService.findFilteredHistoryAnswers(answersId, isMyQuestion);
    }

    // 문항 ID로 오답노트 저장 유무 조회
    public boolean isSavedToIncorrectNote(Long userId, Long questionId) {
        return commonService.isSavedToIncorrectNote(userId, questionId);
    }

}
