package jihye.backend_mock_exam.service.menu.myQuestions.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jihye.backend_mock_exam.domain.exam.Answer;
import lombok.Data;

@Data
public class MyQuestionEditDto {

    @NotNull
    private Long questionId;
    @NotNull(message = "난이도를 선택해주세요")
    private Integer level;
    @NotBlank(message = "문제의 질문을 입력해주세요")
    private String questionText;
    @NotNull
    private Answer correctAnswer;
    @NotNull
    private Answer wrongAnswer1;
    @NotNull
    private Answer wrongAnswer2;
    @NotNull
    private Answer wrongAnswer3;

    public MyQuestionEditDto() {
    }

    public MyQuestionEditDto(Long questionId, Integer level, String questionText, Answer correctAnswer, Answer wrongAnswer1, Answer wrongAnswer2, Answer wrongAnswer3) {
        this.questionId = questionId;
        this.level = level;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
    }
}
