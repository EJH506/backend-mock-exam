package jihye.backend_mock_exam.service.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GuestStartDto {

    @NotBlank
    private String nickname;

}
