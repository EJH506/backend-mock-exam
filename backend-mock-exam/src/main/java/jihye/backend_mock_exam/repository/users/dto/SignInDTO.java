package jihye.backend_mock_exam.repository.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInDTO {

    @NotBlank
    private String accountId;
    @NotBlank
    private String password;

    private boolean rememberMe;

    public SignInDTO() {
    }

    public SignInDTO(String accountId) {
        this.accountId = accountId;
    }
}