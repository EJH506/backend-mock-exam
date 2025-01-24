package jihye.backend_mock_exam.service.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ForgotPasswordQuestionDto {

    private String accountId;
    @NotBlank(message = "비밀번호 찾기 질문을 선택해주세요")
    private String findPasswordQuestion;
    @NotEmpty(message = "비밀번호 찾기 답변을 입력해주세요")
    private String findPasswordAnswer;

}
