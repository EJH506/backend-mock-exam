package jihye.backend_mock_exam.domain.exam;

import lombok.Data;

import java.util.List;

@Data
public class QuestionItem {

    private Question question;
    private List<Answer> answers;

    public QuestionItem() {
    }

    public QuestionItem(Question question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }
}
