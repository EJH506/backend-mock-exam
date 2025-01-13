package jihye.backend_mock_exam.domain.exam;

import lombok.Data;

@Data
public class Question {

    private Long questionId;
    private Long subjectId;
    private String subjectName;
    private int level;
    private String questionText;
}
