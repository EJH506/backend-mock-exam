package jihye.backend_mock_exam.service.menu.exam;

import jihye.backend_mock_exam.controller.menu.ExamConst;
import jihye.backend_mock_exam.domain.exam.*;
import jihye.backend_mock_exam.repository.menu.exam.ExamRepository;
import jihye.backend_mock_exam.service.menu.exam.dto.SubmittedExamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        }

        // 통합 문제일 시
        return examRepository.findMinMaxLevel();
    }

    // 주제,난이도별 문제 수 조회
    @Override
    public Long NumberOfSubject(String subjectName, String level) {

        QuestionFilter questionFilter = questionFilterConvert(subjectName, level);

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

    // 주제,난이도,문항수에 해당하는 문제 목록 조회 (순서 랜덤)
    @Override
    public List<Question> shuffledQuestionList(String subjectName, String level, int number) {

        QuestionFilter questionFilter = questionFilterConvert(subjectName, level);
        List<Question> questions = examRepository.findShuffledQuestions(questionFilter.getSubjectId(), questionFilter.getLevelInt(), number);
        
        // 주제 이름까지 담아서 반환
        for (Question question : questions) {
            Subject subject = examRepository.findSubjectById(question.getSubjectId());
            question.setSubjectName(subject.getSubjectName());
        }

        return questions;
    }

    // 문제의 보기 조회 (순서 랜덤)
    @Override
    public List<Answer> shuffledAnswerListByQuestion(Long questionId) {
        return examRepository.findShuffledAnswers(questionId);
    }

    // 시험 만들어 반환
    @Override
    public List<QuestionItem> createExam(List<Question> questions) {
        ArrayList<QuestionItem> exam = new ArrayList<>();

        for (Question question : questions) {
            QuestionItem questionItem = new QuestionItem();
            questionItem.setQuestion(question);

            List<Answer> answers = shuffledAnswerListByQuestion(question.getQuestionId());
            questionItem.setAnswers(answers);

            exam.add(questionItem);
        }

        return exam;
    }

    // 시험 히스토리 생성
    @Override
    public ExamHistory createExamHistory(SubmittedExamDto dto) {

        ExamHistory examHistory = new ExamHistory(dto.getUserId(), dto.getSubjectName(), dto.getLevel(), dto.getQuestions(), dto.getUserAnswers(), dto.getTotalQuestionsCount());
        ArrayList<Long> correctAnswers = new ArrayList<>();
        List<Long> incorrectQuestions = new ArrayList<>();

        int totalQuestionCount = examHistory.getTotalQuestionsCount();

        // 사용자의 답과 비교할 정답 목록 담기
        for (Long question : dto.getQuestions()) {
            correctAnswers.add(examRepository.findCorrectAnswerByQuestion(question));
        }
        examHistory.setCorrectAnswers(correctAnswers);

        // 틀린 문항 담기
        for (int i=0; i < totalQuestionCount; i++) {
            if (!Objects.equals(examHistory.getCorrectAnswers().get(i), examHistory.getUserAnswers().get(i))) {
                incorrectQuestions.add(examHistory.getQuestions().get(i));
            }
        }
        examHistory.setIncorrectQuestions(incorrectQuestions);

        // 정답률 연산하여 담기
        int correctQuestionsCount = examHistory.getTotalQuestionsCount() - examHistory.getIncorrectQuestions().size();
        double correctRate = ((double) correctQuestionsCount / totalQuestionCount) * 100;

        examHistory.setCorrectQuestionsCount(correctQuestionsCount);
        examHistory.setCorrectRate(correctRate);

        // DB에 저장 (회원일 시 저장, 비회원일 시 세션 등)
        if (examHistory.getUserId() > 0) {
            examRepository.saveExamHistory(examHistory);
        }

        return examHistory;
    }

    // 히스토리 상세 반환
    @Override
    public List<HistoryItem> createHistoryDetails(List<Long> questionsId, List<Long> correctAnswersId, List<Long> userAnswersId) {
        List<HistoryItem> historyDetails = new ArrayList<>();
        List<Question> questions = findFilteredHistoryQuestions(questionsId);
        List<Answer> correctAnswers = findFilteredHistoryAnswers(correctAnswersId);
        List<Answer> userAnswers = findFilteredHistoryAnswers(userAnswersId);

        for (int i=0; i<questions.size(); i++) {
            historyDetails.add(new HistoryItem(questions.get(i), correctAnswers.get(i), userAnswers.get(i)));
        }

        return historyDetails;
    }

    // 조건에 맞는 문항 조회
    private List<Question> findFilteredHistoryQuestions(List<Long> questionsId) {

        List<Question> questions = new ArrayList<>();
        
        for (Long questionId : questionsId) {
            Question question = examRepository.findQuestionsById(questionId);
            Subject subject = examRepository.findSubjectById(question.getSubjectId());
            question.setSubjectName(subject.getSubjectName());
            questions.add(question);
        }

        return questions;
    }

    // 조건에 맞는 보기 조회
    private List<Answer> findFilteredHistoryAnswers(List<Long> answersId) {

        List<Answer> correctAnswers = new ArrayList<>();

        for (Long answerId : answersId) {
            correctAnswers.add(examRepository.findAnswerById(answerId));
        }

        return correctAnswers;
    }

    // 매개변수로 사용될 subject와 level의 값을 통합인지 아닌지에 따라 적절히 변환
    private QuestionFilter questionFilterConvert(String subjectName, String level) {

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

        return new QuestionFilter(subjectId, levelInt);
    }

    public static class QuestionFilter {

        private Long subjectId;
        private int levelInt;

        public QuestionFilter(Long subjectId, int levelInt) {
            this.subjectId = subjectId;
            this.levelInt = levelInt;
        }

        public Long getSubjectId() {
            return subjectId;
        }

        public int getLevelInt() {
            return levelInt;
        }
    }
}
