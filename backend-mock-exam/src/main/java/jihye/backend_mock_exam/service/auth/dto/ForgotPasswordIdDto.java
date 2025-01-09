package jihye.backend_mock_exam.service.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgotPasswordIdDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String accountId;

}
