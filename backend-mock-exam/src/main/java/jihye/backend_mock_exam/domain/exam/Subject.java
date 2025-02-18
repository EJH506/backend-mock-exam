package jihye.backend_mock_exam.domain.exam;

import lombok.Data;

import java.util.List;

@Data
public class Subject {

    private Long subjectId;
    private String subjectName;
    private List<Integer> levels;

    public Subject() {
    }

    public Subject(Long subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public Subject(Long subjectId, String subjectName, List<Integer> levels) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.levels = levels;
    }
}
