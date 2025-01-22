package jihye.backend_mock_exam.service.menu.memo.dto;

import lombok.Data;

@Data
public class MemoEditDto {

    private Long memoId;
    private String memoText;

    public MemoEditDto() {
    }

    public MemoEditDto(Long memoId, String memoText) {
        this.memoId = memoId;
        this.memoText = memoText;
    }
}
