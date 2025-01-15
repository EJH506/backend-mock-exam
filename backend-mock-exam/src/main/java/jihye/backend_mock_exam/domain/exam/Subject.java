package jihye.backend_mock_exam.domain.exam;

import lombok.Data;

@Data
public class Subject {

    private Long subjectId;
    private String subjectName;

    public Subject() {
    }

    public Subject(Long subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }
}
