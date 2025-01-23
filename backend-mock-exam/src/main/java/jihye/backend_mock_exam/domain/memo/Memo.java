package jihye.backend_mock_exam.domain.memo;

import lombok.Data;

@Data
public class Memo {
    private Long memoId;
    private Long userId;
    private String memoText;
    private String createdAt;
    private String updateAt;

    public Memo() {
    }

    public Memo(Long userId, String memoText) {
        this.userId = userId;
        this.memoText = memoText;
    }
}
