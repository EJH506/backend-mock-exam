package jihye.backend_mock_exam.domain.myQuestion;

import lombok.Data;

@Data
public class MyQuestion {

    private Long questionId;
    private Long userId;
    private Integer level;
    private String questionText;
    private boolean deleted;
    private String createdAt;
    private String updateAt;
}
