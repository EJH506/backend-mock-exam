package jihye.backend_mock_exam.repository.user.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInDTO {

    @NotBlank
    private String loginId;
    @NotBlank
    private String loginPassword;

    private boolean rememberMe;

    public SignInDTO() {
    }

    public SignInDTO(String loginId) {
        this.loginId = loginId;
    }
}