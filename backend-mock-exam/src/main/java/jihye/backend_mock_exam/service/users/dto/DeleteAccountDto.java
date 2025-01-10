package jihye.backend_mock_exam.service.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeleteAccountDto {

    private Long userId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
