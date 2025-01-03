package jihye.backend_mock_exam.repository.user.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignUpDTO {

    @NotBlank
    private String accountId;
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordCheck;
    @NotBlank
    private String findPasswordQuestion;
    @NotEmpty
    private String findPasswordAnswer;

    private String gender;
    private String birthYear;
}
