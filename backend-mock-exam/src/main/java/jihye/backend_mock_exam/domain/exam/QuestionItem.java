package jihye.backend_mock_exam.domain.exam;

import lombok.Data;

import java.util.List;

@Data
public class QuestionItem {

    private Question question;
    private List<Answer> answers;
}
