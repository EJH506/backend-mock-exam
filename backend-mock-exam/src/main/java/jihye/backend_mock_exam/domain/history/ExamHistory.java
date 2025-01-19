package jihye.backend_mock_exam.domain.history;

import lombok.Data;

@Data
public class ExamHistory {

    private Long historyId;
    private Long userId;
    private String subjectName;
    private int level;
    private int totalQuestionsCount;
    private int correctQuestionsCount;     // 맞힌 문항 수
    private double correctRate;            // 정답률
    private String createdAt;

    public ExamHistory() {
    }

    public ExamHistory(Long userId, String subjectName, int level, int totalQuestionsCount, int correctQuestionsCount, double correctRate) {
        this.userId = userId;
        this.subjectName = subjectName;
        this.level = level;
        this.totalQuestionsCount = totalQuestionsCount;
        this.correctQuestionsCount = correctQuestionsCount;
        this.correctRate = correctRate;
    }
}
