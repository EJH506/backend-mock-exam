package jihye.backend_mock_exam.service.menu.memo.dto;

import lombok.Data;

@Data
public class MemoWriteDto {

    private Long userId;
    private String memoText;

    public MemoWriteDto() {
    }

    public MemoWriteDto(Long userId, String memoText) {
        this.userId = userId;
        this.memoText = memoText;
    }
}
