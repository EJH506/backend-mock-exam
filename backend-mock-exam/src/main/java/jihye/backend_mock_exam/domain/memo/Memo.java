package jihye.backend_mock_exam.domain.memo;

import lombok.Data;

@Data
public class Memo {
    private Long memoId;
    private Long userId;
    private String memoText;
    private String createdAt;
    private String updateAt;
}
