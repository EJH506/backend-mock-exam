package jihye.backend_mock_exam.service.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResetPasswordDto {

    private String accountId;

    @NotBlank(message = "새 비밀번호를 입력해주세요")
    private String password;
    @NotBlank(message = "새 비밀번호 확인란을 입력해주세요")
    private String passwordCheck;

}
