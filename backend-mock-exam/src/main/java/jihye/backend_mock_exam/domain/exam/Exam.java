package jihye.backend_mock_exam.domain.exam;

import lombok.Data;

import java.util.List;

@Data
public class Exam {

    private String subjectName;
    private String level;
    private int number;

    private List<ExamItem> examItems;

    public Exam(String subjectName, String level, int number, List<ExamItem> examItems) {
        this.subjectName = subjectName;
        this.level = level;
        this.number = number;
        this.examItems = examItems;
    }
}
