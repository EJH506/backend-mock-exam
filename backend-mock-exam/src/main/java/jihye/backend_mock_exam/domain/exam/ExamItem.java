package jihye.backend_mock_exam.domain.exam;

import jihye.backend_mock_exam.domain.Questions;
import lombok.Data;

import java.util.List;

@Data
public class ExamItem {

    private Questions question;
    private List<Answer> answers;
    private Answer correctAnswer;

    public ExamItem() {
    }

    public ExamItem(Question question, List<Answer> answers, Answer correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }
}
