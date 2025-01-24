package jihye.backend_mock_exam.service.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GuestStartDto {

    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;

}
