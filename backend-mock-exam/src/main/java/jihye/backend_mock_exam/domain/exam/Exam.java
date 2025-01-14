package jihye.backend_mock_exam.domain.exam;

import lombok.Data;

import java.util.List;

@Data
public class Exam {

    private String subject;
    private String level;
    private int totalQuestionsCount;
    private List<ExamItem> examItems;

    public Exam() {
    }

    public Exam(String subject, String level, int totalQuestionsCount, List<ExamItem> examItems) {
        this.subject = subject;
        this.level = level;
        this.totalQuestionsCount = totalQuestionsCount;
        this.examItems = examItems;
    }
}
