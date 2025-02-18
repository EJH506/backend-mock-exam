package jihye.backend_mock_exam.service.admin;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminSubjectEditDto {

    @NotBlank(message = "주제 이름을 입력해주세요")
    private String subjectName;

    public AdminSubjectEditDto() {
    }

    public AdminSubjectEditDto(String subjectName) {
        this.subjectName = subjectName;
    }
}
