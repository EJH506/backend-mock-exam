package jihye.backend_mock_exam.domain.exam;

import lombok.Data;

@Data
public class HistoryItemObject {

    private Question question;
    private Answer correctAnswer;
    private Answer userAnswer;

    public HistoryItemObject() {
    }

    public HistoryItemObject(Question question, Answer correctAnswer, Answer userAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.userAnswer = userAnswer;
    }
}
