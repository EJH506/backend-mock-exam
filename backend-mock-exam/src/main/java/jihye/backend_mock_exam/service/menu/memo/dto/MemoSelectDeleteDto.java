package jihye.backend_mock_exam.service.menu.memo.dto;

import lombok.Data;

import java.util.List;

@Data
public class MemoSelectDeleteDto {

    private List<Long> deleteMemosId;
}
