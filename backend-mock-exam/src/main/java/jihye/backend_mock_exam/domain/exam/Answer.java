package jihye.backend_mock_exam.domain.exam;

import lombok.Data;

@Data
public class Answer {

    private Long answerId;
    private Long questionId;
    private String answerText;
    private boolean isCorrect;
}
