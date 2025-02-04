package jihye.backend_mock_exam.domain.history;

import jihye.backend_mock_exam.domain.Questions;
import jihye.backend_mock_exam.domain.exam.Answer;
import jihye.backend_mock_exam.domain.exam.Question;
import lombok.Data;

@Data
public class HistoryItemObject {

    private Questions question;
    private Answer correctAnswer;
    private Answer userAnswer;
    private boolean isSaved;

    public HistoryItemObject() {
    }

    public HistoryItemObject(Questions question, Answer correctAnswer, Answer userAnswer, boolean isSaved) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.userAnswer = userAnswer;
        this.isSaved = isSaved;
    }
}
