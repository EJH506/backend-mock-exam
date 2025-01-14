package jihye.backend_mock_exam.domain.exam;

import lombok.Data;

@Data
public class HistoryItem {

    private Long historyItemsId;
    private Long historyId;
    private Long questionId;
    private Long correctAnswerId;
    private Long userAnswerId;
    private boolean isCorrect;

    public HistoryItem(Long historyId, Long questionId, Long correctAnswerId, Long userAnswerId, boolean isCorrect) {
        this.historyId = historyId;
        this.questionId = questionId;
        this.correctAnswerId = correctAnswerId;
        this.userAnswerId = userAnswerId;
        this.isCorrect = isCorrect;
    }
}
