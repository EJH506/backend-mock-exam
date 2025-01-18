package jihye.backend_mock_exam.service.menu;

import lombok.Data;

@Data
public class QuestionFilter {

    private Long subjectId;
    private int levelInt;

    public QuestionFilter(Long subjectId, int levelInt) {
        this.subjectId = subjectId;
        this.levelInt = levelInt;
    }

}
