package jihye.backend_mock_exam.domain.history;

import jihye.backend_mock_exam.domain.exam.Answer;
import jihye.backend_mock_exam.domain.exam.Question;

//@Data
public class HistoryItemObject {

    private Question question;
    private Answer correctAnswer;
    private Answer userAnswer;

    public HistoryItemObject() {
    }
}
