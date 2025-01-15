package jihye.backend_mock_exam.domain.incorrectNote;

import jihye.backend_mock_exam.domain.exam.Answer;
import jihye.backend_mock_exam.domain.exam.Question;
import lombok.Data;

import java.util.List;

@Data
public class IncorrectItem {

    private Question question;
    private List<Answer> answers;
    private Answer correctAnswer;
    private Long subjectId;
    private int level;
    private boolean isSaved;
}
