package jihye.backend_mock_exam.domain.incorrectNote;

import lombok.Data;

@Data
public class IncorrectNote {

    private Long incorrectNoteId;
    private Long questionId;
    private Long subjectId;
    private int level;
}
