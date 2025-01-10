package jihye.backend_mock_exam.service.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EditAccountDto {

    private Long userId;
    @NotBlank
    private String accountId;
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    private String gender;
    private Integer birthYear;

    public EditAccountDto() {
    }

    public EditAccountDto(Long userId, String accountId, String nickname, String gender, Integer birthYear) {
        this.userId = userId;
        this.accountId = accountId;
        this.nickname = nickname;
        this.gender = gender;
        this.birthYear = birthYear;
    }
}
