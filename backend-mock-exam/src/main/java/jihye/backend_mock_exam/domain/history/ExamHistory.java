package jihye.backend_mock_exam.domain.history;

import jihye.backend_mock_exam.domain.Questions;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ExamHistory {

    private Long historyId;
    private Long userId;
    private String subjectName;
    private int level;
    private List<Boolean> isMyQuestions;    // 각 문항이 나만의 문제인지 여부
    private List<Long> questions;          // 문항 목록
    private List<Long> userAnswers;        // 제출된 답
    private List<Long> correctAnswers;     // 정답
    private List<Long> incorrectQuestions; // 틀린 문항
    private int correctQuestionsCount;     // 맞힌 문항 수
    private double correctRate;            // 정답률

    private int totalQuestionsCount;
    private String createdAt;


    public ExamHistory() {
    }

    public ExamHistory(Long userId, String subjectName, int level, List<Boolean> isMyQuestions, List<Long> questions, List<Long> userAnswers, int totalQuestionsCount) {
        this.userId = userId;
        this.subjectName = subjectName;
        this.level = level;
        this.isMyQuestions = isMyQuestions;
        this.questions = questions;
        this.userAnswers = userAnswers;
        this.totalQuestionsCount = totalQuestionsCount;
    }
}
