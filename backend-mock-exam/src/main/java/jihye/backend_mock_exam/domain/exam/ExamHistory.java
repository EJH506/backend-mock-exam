package jihye.backend_mock_exam.domain.exam;

import lombok.Data;

import java.util.List;

@Data
public class ExamHistory {

    private Long userId;
    private String subjectName;
    private int level;
    private List<Long> questions;
    private List<Long> answers;
    private List<Long> correctAnswers;

    private int totalQuestionsCount;


    public ExamHistory() {
    }

    public ExamHistory(Long userId, String subjectName, int level, List<Long> questions, List<Long> answers, int totalQuestionsCount) {
        this.userId = userId;
        this.subjectName = subjectName;
        this.level = level;
        this.questions = questions;
        this.answers = answers;
        this.totalQuestionsCount = totalQuestionsCount;
    }
}
