package jihye.backend_mock_exam.domain.exam;

import lombok.Data;

import java.util.List;

@Data
public class ExamHistory {

    private Long historyId;
    private Long userId;
    private Long subjectId;
    private Integer level;
    private List<Long> questions;          // 문항 목록
    private List<Long> userAnswers;        // 제출된 답
    private List<Long> correctAnswers;     // 정답
    private List<Long> incorrectQuestions; // 틀린 문항
    private int correctQuestionsCount;     // 맞힌 문항 수
    private double correctRate;            // 정답률

    private int totalQuestionsCount;


    public ExamHistory() {
    }

    public ExamHistory(Long userId, Long subjectId, int level, List<Long> questions, List<Long> userAnswers, int totalQuestionsCount) {
        this.userId = userId;
        this.subjectId = subjectId;
        this.level = level;
        this.questions = questions;
        this.userAnswers = userAnswers;
        this.totalQuestionsCount = totalQuestionsCount;
    }
}
