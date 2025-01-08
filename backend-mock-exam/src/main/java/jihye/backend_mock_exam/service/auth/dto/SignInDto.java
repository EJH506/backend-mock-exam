package jihye.backend_mock_exam.service.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInDto {

    @NotBlank
    private String accountId;
    @NotBlank
    private String password;

    private boolean rememberMe;

    public SignInDto() {
    }

    public SignInDto(String accountId) {
        this.accountId = accountId;
    }
}