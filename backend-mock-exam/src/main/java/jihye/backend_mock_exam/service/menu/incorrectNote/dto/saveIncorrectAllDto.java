package jihye.backend_mock_exam.service.menu.incorrectNote.dto;

import lombok.Data;

import java.util.List;

@Data
public class saveIncorrectAllDto {

    private Long userId;
    private List<WrongQuestion> wrongQuestions;

    @Data
    public static class WrongQuestion {
        private Long questionId;
        private Boolean isMyQuestion;
        private Boolean isSaved;
    }

}
