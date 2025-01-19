package jihye.backend_mock_exam.service.menu;

import jihye.backend_mock_exam.controller.menu.ExamConst;
import jihye.backend_mock_exam.domain.exam.*;
import jihye.backend_mock_exam.domain.history.ExamHistory;
import jihye.backend_mock_exam.domain.history.HistoryItem;
import jihye.backend_mock_exam.domain.history.HistoryItemObject;
import jihye.backend_mock_exam.repository.menu.exam.ExamRepository;
import jihye.backend_mock_exam.repository.menu.history.HistoryRepository;
import jihye.backend_mock_exam.repository.menu.incorrectNote.IncorrectNoteRepository;
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
public class CommonServiceImpl implements CommonService {

    private final ExamRepository examRepository;
    private final IncorrectNoteRepository incorrectNoteRepository;
    private final HistoryRepository historyRepository;

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
    public List<Integer> levelListOfSubject(String subjectName) {

        // 통합 문제가 아닐 시
        if (!(ExamConst.SUBJECT_ALL.equals(subjectName))) {
            return examRepository.findLevelsBySubject(subjectName);
        }

        // 통합 문제일 시
        return examRepository.findMinMaxLevel();
    }

    // 문제의 보기 조회 (순서 랜덤)
    @Override
    public List<Answer> shuffledAnswerListByQuestion(Long questionId) {
        return examRepository.findShuffledAnswers(questionId);
    }

    @Override
    // 조건에 맞는 문항 조회
    public List<Question> findFilteredHistoryQuestions(List<Long> questionsId) {

        List<Question> questions = new ArrayList<>();

        for (Long questionId : questionsId) {
            Question question = examRepository.findQuestionsById(questionId);
            Subject subject = examRepository.findSubjectById(question.getSubjectId());
            question.setSubjectName(subject.getSubjectName());
            questions.add(question);
        }

        return questions;
    }

    // 시험 히스토리 생성
    @Override
    public ExamHistory createExamHistory(SubmittedExamDto dto) {

        QuestionFilter questionFilter = questionFilterConvert(dto.getSubjectName(), dto.getLevel());
        int totalQuestionsCount = dto.getTotalQuestionsCount();

        ArrayList<Long> correctAnswers = new ArrayList<>();
        List<Long> incorrectQuestions = new ArrayList<>();

        // 사용자의 답과 비교할 정답 목록 담기
        for (Long question : dto.getQuestions()) {
            correctAnswers.add(examRepository.findCorrectAnswerByQuestion(question).getAnswerId());
        }

        // 틀린 문항 담기
        for (int i=0; i < totalQuestionsCount; i++) {
            if (!Objects.equals(correctAnswers.get(i), dto.getUserAnswers().get(i))) {
                incorrectQuestions.add(dto.getQuestions().get(i));
            }
        }

        // 정답률 연산하여 담기
        int correctQuestionsCount = totalQuestionsCount - incorrectQuestions.size();
        double correctRate = ((double) correctQuestionsCount / totalQuestionsCount) * 100;

        ExamHistory examHistory = new ExamHistory(dto.getUserId(), dto.getSubjectName(), questionFilter.getLevelInt(), totalQuestionsCount, correctQuestionsCount, correctRate);

        // DB에 저장 (회원일 시 저장, 비회원일 시 세션 등)
        if (examHistory.getUserId() > 0) {
            // exam_history (시험 정보 및 결과)
            historyRepository.saveExamHistory(examHistory);

            // history_items (시험 문제)
            Long historyId = examHistory.getHistoryId();

            for (int i=0; i < examHistory.getTotalQuestionsCount(); i++) {
                Long correctAnswer = examRepository.findCorrectAnswerByQuestion(dto.getQuestions().get(i)).getAnswerId();
                Long userAnswer = dto.getUserAnswers().get(i);

                boolean isCorrect = false;

                if (correctAnswer.equals(userAnswer)) {
                    isCorrect = true;
                }
                historyRepository.saveExamHistoryItems(new HistoryItem(historyId, dto.getQuestions().get(i), correctAnswer, userAnswer, isCorrect));
            }

        }

        return examHistory;
    }

    // 히스토리 상세 반환
    @Override
    public List<HistoryItemObject> createHistoryDetails(ExamHistory examHistory, String option) {

        List<HistoryItemObject> historyDetails = new ArrayList<>();

        log.info("examHistory={}", examHistory);

//        List<Question> questions = findFilteredHistoryQuestions(examHistory.getQuestions());
//        List<Answer> correctAnswers = findFilteredHistoryAnswers(examHistory.getCorrectAnswers());
//        List<Answer> userAnswers = findFilteredHistoryAnswers(examHistory.getUserAnswers());


//        List<Question> questions = findQuestionsOfHistory(examHistory.getHistoryId(), false);
        List<Question> questions = historyRepository.findQuestionsOfHistory(examHistory.getHistoryId(), false);
        List<HistoryItem> historyItems = new ArrayList<>();

        for (Question question : questions) {
        }

        for (int i=0; i<questions.size(); i++) {

            if ("correctOnly".equals(option)) {
                if (correctAnswers.get(i).equals(userAnswers.get(i))) {
                    historyDetails.add(new HistoryItemObject(questions.get(i), correctAnswers.get(i), userAnswers.get(i), incorrectNoteSaved));
                }
            }

            if ("incorrectOnly".equals(option)) {
                if (!correctAnswers.get(i).equals(userAnswers.get(i))) {
                    historyDetails.add(new HistoryItemObject(questions.get(i), correctAnswers.get(i), userAnswers.get(i), incorrectNoteSaved));
                }
            }

            if ("all".equals(option)) {
                historyDetails.add(new HistoryItemObject(questions.get(i), correctAnswers.get(i), userAnswers.get(i), incorrectNoteSaved));
            }
        }

        return historyDetails;
    }


    // 히스토리에 속한 문제 조회
//    @Override
//    public List<Question> findQuestionsOfHistory(Long historyId, boolean isCorrect) {
//        return historyRepository.findQuestionsOfHistory(historyId, isCorrect);
//    }

    @Override
    // 조건에 맞는 보기 조회
    public List<Answer> findFilteredHistoryAnswers(List<Long> answersId) {

        List<Answer> correctAnswers = new ArrayList<>();

        for (Long answerId : answersId) {
            correctAnswers.add(examRepository.findAnswerById(answerId));
        }

        return correctAnswers;
    }

    // 문항 ID로 오답노트 저장 유무 조회
    @Override
    public boolean isSavedToIncorrectNote(Long userId, Long questionId) {
        return incorrectNoteRepository.findIncorrectNoteById(userId, questionId) != null;
    }

    @Override
    // 매개변수로 사용될 subject와 level의 값을 통합인지 아닌지에 따라 적절히 변환
    public QuestionFilter questionFilterConvert(String subjectName, String level) {

        Long subjectId = 0L;
        int levelInt = 0;

        // 통합 문제가 아닐 시
        if (subjectName != null && !(ExamConst.SUBJECT_ALL.equals(subjectName))) {
            subjectId = findSubjectByName(subjectName).getSubjectId();
        }

        // 전체 레벨이 아닐 시
        if (level != null && !(ExamConst.LEVEL_ALL.equals(level))) {
            levelInt = Integer.parseInt(level);
        }

        return new QuestionFilter(subjectId, levelInt);
    }
}
