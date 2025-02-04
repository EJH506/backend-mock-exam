package jihye.backend_mock_exam.service.menu;

import jihye.backend_mock_exam.controller.menu.ExamConst;
import jihye.backend_mock_exam.domain.Questions;
import jihye.backend_mock_exam.domain.exam.*;
import jihye.backend_mock_exam.domain.history.ExamHistory;
import jihye.backend_mock_exam.domain.history.HistoryItem;
import jihye.backend_mock_exam.domain.history.HistoryItemObject;
import jihye.backend_mock_exam.domain.myQuestion.MyQuestion;
import jihye.backend_mock_exam.repository.menu.exam.ExamRepository;
import jihye.backend_mock_exam.repository.menu.history.HistoryRepository;
import jihye.backend_mock_exam.repository.menu.incorrectNote.IncorrectNoteRepository;
import jihye.backend_mock_exam.repository.menu.myQuestions.MyQuestionsRepository;
import jihye.backend_mock_exam.service.menu.exam.dto.SubmittedExamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final MyQuestionsRepository myQuestionsRepository;

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
    public List<Integer> levelListOfSubject(Long userId, String subjectName) {

        // 나만의 문제일 시
        if (ExamConst.SUBJECT_MYQUESTIONS.equals(subjectName)) {
            return myQuestionsRepository.findLevelsOfMyQuestion(userId);
        }

        // 통합 문제가 아닐 시
        if (!(ExamConst.SUBJECT_ALL.equals(subjectName))) {
            return examRepository.findLevelsBySubject(subjectName);
        }

        // 통합 문제일 시
        return examRepository.findMinMaxLevel();
    }

    // 주제별 난이도 목록 조회 (문항이 존재하는 것만 조회)
    @Override
    public List<Integer> levelListOfSubjectWithItem(Long userId, String subjectName) {

        // 나만의 문제일 시
        if (ExamConst.SUBJECT_MYQUESTIONS.equals(subjectName)) {
            return myQuestionsRepository.findLevelsOfMyQuestionWithItem(userId);
        }

        // 통합 문제가 아닐 시
        if (!(ExamConst.SUBJECT_ALL.equals(subjectName))) {
            return examRepository.findLevelsBySubjectWithItem(subjectName);
        }

        // 통합 문제일 시
        return examRepository.findMinMaxLevelWithItem();
    }

    @Override
    @Transactional
    // 조건에 맞는 문항 조회
    public List<Questions> findFilteredHistoryQuestions(List<Long> questionsId, List<Boolean> isMyQuestions) {

        List<Questions> questions = new ArrayList<>();

        for (int i=0; i<questionsId.size(); i++) {
            Questions question;
            
            // 나만의 문제일 경우
            if (isMyQuestions.get(i)) {
                question = myQuestionsRepository.findMyQuestionById(questionsId.get(i));

            // 아닐경우
            } else {
                question = examRepository.findQuestionsById(questionsId.get(i));
            }
            questions.add(question);
        }

        return questions;
    }

    // 문제의 보기 조회 (순서 랜덤)
    @Override
    public List<Answer> shuffledAnswerListByQuestion(Questions question, boolean isMyQuestion) {

        // 나만의 문제일 경우
        if (ExamConst.SUBJECT_MYQUESTIONS.equals(question.getSubjectName())) {
            return myQuestionsRepository.findShuffledMyQuestionsAnswers(question.getQuestionId());

        // 아닐 경우
        } else {
            return examRepository.findShuffledAnswers(question.getQuestionId());
        }
    }

    // 시험 히스토리 생성
    @Override
    @Transactional
    public ExamHistory createExamHistory(SubmittedExamDto dto) {

        QuestionFilter questionFilter = questionFilterConvert(dto.getSubject(), dto.getLevel());
        ExamHistory examHistory = new ExamHistory(dto.getUserId(), dto.getSubject(), questionFilter.getLevelInt(), dto.getIsMyQuestions(), dto.getQuestions(), dto.getUserAnswers(), dto.getTotalQuestionsCount());

        ArrayList<Long> correctAnswers = new ArrayList<>();
        List<Long> incorrectQuestions = new ArrayList<>();

        int totalQuestionCount = examHistory.getTotalQuestionsCount();

        // 사용자의 답과 비교할 정답 목록 담기
        for (int i=0; i<dto.getQuestions().size(); i++) {
            // 만약 현재 문항이 나만의 문제일 경우
            if (dto.getIsMyQuestions().get(i)) {
                correctAnswers.add(myQuestionsRepository.findCorrectAnswerByMyQuestion(dto.getQuestions().get(i)));
                        
            // 아닐 경우
            } else {
                correctAnswers.add(examRepository.findCorrectAnswerByQuestion(dto.getQuestions().get(i)));
            }
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
            // 시험 결과
            historyRepository.saveExamHistory(examHistory);

            // 시험 문제
            Long historyId = examHistory.getHistoryId();

            for (int i=0; i < examHistory.getTotalQuestionsCount(); i++) {
                Long question = examHistory.getQuestions().get(i);
                boolean isMyQuestion = examHistory.getIsMyQuestions().get(i);
                Long correctAnswer = examRepository.findCorrectAnswerByQuestion(question);
                Long userAnswer = examHistory.getUserAnswers().get(i);

                boolean isCorrect = false;

                if (correctAnswer.equals(userAnswer)) {
                    isCorrect = true;
                }

                historyRepository.saveExamHistoryItems(new HistoryItem(historyId, isMyQuestion, question, correctAnswer, userAnswer, isCorrect));
            }

        }
        
        // DB 저장 후 화면에 출력할 정답률 가공해서 재할당
        examHistory.setCorrectRate(Math.round(examHistory.getCorrectRate() * 10) / 10.0);

        return examHistory;
    }

    // 히스토리 상세 반환
    @Override
    public List<HistoryItemObject> createHistoryDetails(ExamHistory examHistory, String option) {
        List<HistoryItemObject> historyDetails = new ArrayList<>();

        List<Questions> questions = findFilteredHistoryQuestions(examHistory.getQuestions(), examHistory.getIsMyQuestions());
        List<Answer> correctAnswers = findFilteredHistoryAnswers(examHistory.getCorrectAnswers(), examHistory.getIsMyQuestions());
        List<Answer> userAnswers = findFilteredHistoryAnswers(examHistory.getUserAnswers(), examHistory.getIsMyQuestions());

        for (int i=0; i<questions.size(); i++) {

            boolean incorrectNoteSaved = isSavedToIncorrectNote(examHistory.getUserId(), questions.get(i).getQuestionId(), examHistory.getIsMyQuestions().get(i));

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

        log.info("historyDetails={}", historyDetails);
        return historyDetails;
    }

    // 히스토리 조회
    @Override
    public ExamHistory findExamHistoryById(Long historyId) {
        return historyRepository.findExamHistoryById(historyId);
    }

    // 히스토리에 속한 문제 조회 (전체를 찾으려면 true, 틀린문제만이면 false)
    @Override
    public List<Questions> findQuestionsIdOfHistory(Long historyId, boolean isCorrect) {
        return historyRepository.findQuestionsIdOfHistory(historyId, isCorrect);
    }

    @Override
    // 조건에 맞는 보기 조회
    public List<Answer> findFilteredHistoryAnswers(List<Long> answersId, List<Boolean> isMyQuestions) {

        List<Answer> correctAnswers = new ArrayList<>();

        for (int i=0; i<answersId.size(); i++) {
            // 나만의 문제인지 아닌지에 따라 보기 조회
            Answer answer = isMyQuestions.get(i) ? myQuestionsRepository.findMyQuestionsAnswerById(answersId.get(i)) : examRepository.findAnswerById(answersId.get(i));
            correctAnswers.add(answer);
        }

        return correctAnswers;
    }

    // 문항 ID로 오답노트 저장 유무 조회
    @Override
    public boolean isSavedToIncorrectNote(Long userId, Long questionId, boolean isMyQuestion) {
        return incorrectNoteRepository.findIncorrectNoteById(userId, questionId, isMyQuestion) != null;
    }

    @Override
    // 매개변수로 사용될 subject와 level의 값을 통합인지 아닌지에 따라 적절히 변환
    public QuestionFilter questionFilterConvert(String subjectName, String level) {
        Long subjectId = 0L;
        int levelInt = 0;

        // 전체 레벨이 아닐 시
        if (level != null && !(ExamConst.LEVEL_ALL.equals(level))) {
            levelInt = Integer.parseInt(level);
        }

        // 나만의 문제일 시
        if (ExamConst.SUBJECT_MYQUESTIONS.equals(subjectName)) {
            subjectId = null;

        // 통합 문제가 아닐 시
        } else if (subjectName != null && !(ExamConst.SUBJECT_ALL.equals(subjectName))) {
            subjectId = findSubjectByName(subjectName).getSubjectId();
        }

        return new QuestionFilter(subjectId, levelInt);
    }

    @Override
    // 매개변수로 사용될 level의 값을 통합인지 아닌지에 따라 적절히 변환
    public QuestionFilter questionFilterConvert(String level) {

        int levelInt = 0;

        // 전체 레벨이 아닐 시
        if (level != null && !(ExamConst.LEVEL_ALL.equals(level))) {
            levelInt = Integer.parseInt(level);
        }

        return new QuestionFilter(levelInt);
    }

    // 나만의 문제에 존재하는 레벨 목록 보기 (문항이 존재하는 것만 조회)
    @Override
    public List<Integer> levelListOfMyQuestionWithItem(Long userId) {
        return myQuestionsRepository.findLevelsOfMyQuestionWithItem(userId);
    }

    // 사용자의 나만의 문제 문항 수 조회
    @Override
    public Integer findNumberOfMyQuestion(Long userId, int level) {
        return myQuestionsRepository.findNumberOfMyQuestion(userId, level);
    }

    // 나만의 문제의 난이도, 문항수에 해당하는 문제 목록 조회
    @Override
    public List<MyQuestion> findShuffledMyQuestions(Long userId, int level, int number) {
        return myQuestionsRepository.findShuffledMyQuestions(userId, level, number);
    }
}
