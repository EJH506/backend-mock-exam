package jihye.backend_mock_exam.domain.exam;

import jihye.backend_mock_exam.domain.Questions;
import lombok.Data;

@Data
public class Question implements Questions {

    private Long questionId;
    private Long subjectId;
    private String subjectName;
    private int level;
    private String questionText;
    private boolean deleted;
    private String createdAt;
    private String updateAt;

    private Long userId;
}
