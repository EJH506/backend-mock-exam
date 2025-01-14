package jihye.backend_mock_exam.service.menu.exam.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubmittedExamDto {

    private Long userId;
    private String subject;
    private String level;

    private List<Long> questions;
    private List<Long> userAnswers;

    private int totalQuestionsCount;
}
