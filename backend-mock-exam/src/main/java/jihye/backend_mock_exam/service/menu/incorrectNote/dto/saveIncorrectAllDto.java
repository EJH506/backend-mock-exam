package jihye.backend_mock_exam.service.menu.incorrectNote.dto;

import lombok.Data;

import java.util.List;

@Data
public class saveIncorrectAllDto {

    private Long userId;
    private List<wrongQuestion> wrongQuestions;

    @Data
    public static class wrongQuestion {
        private Long questionId;
        private Boolean isSaved;
    }

}
