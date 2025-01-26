package jihye.backend_mock_exam.domain.exam;

import lombok.Data;

@Data
public class Answer {

    private Long answerId;
    private Long questionId;
    private String answerText;
    private boolean isCorrect;

    public Answer() {
    }

    public Answer(Long answerId, Long questionId, String answerText, boolean isCorrect) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }
}
