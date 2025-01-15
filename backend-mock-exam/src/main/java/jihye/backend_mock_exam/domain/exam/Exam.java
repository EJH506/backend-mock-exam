package jihye.backend_mock_exam.domain.exam;

import lombok.Data;

import java.util.List;

@Data
public class Exam {

    private Long subjectId;
    private Integer level;
    private int totalQuestionsCount;
    private List<ExamItem> examItems;

    public Exam() {
    }

    public Exam(Long subjectId, Integer level, int totalQuestionsCount, List<ExamItem> examItems) {
        this.subjectId = subjectId;
        this.level = level;
        this.totalQuestionsCount = totalQuestionsCount;
        this.examItems = examItems;
    }
}
