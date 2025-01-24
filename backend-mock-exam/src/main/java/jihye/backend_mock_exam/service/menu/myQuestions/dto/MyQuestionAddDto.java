package jihye.backend_mock_exam.service.menu.myQuestions.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MyQuestionAddDto {
    
    @NotNull
    private Long userId;
    @NotBlank(message = "난이도를 선택해주세요")
    private int level;
    @NotBlank(message = "문제의 질문을 입력해주세요")
    private String questionText;
    @NotBlank
    private String correctAnswer;
    @NotBlank
    private String wrongAnswer1;
    @NotBlank
    private String wrongAnswer2;
    @NotBlank
    private String wrongAnswer3;

}
