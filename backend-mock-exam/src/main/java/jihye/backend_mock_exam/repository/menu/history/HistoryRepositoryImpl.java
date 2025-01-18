package jihye.backend_mock_exam.repository.menu.history;

import jihye.backend_mock_exam.domain.exam.ExamHistory;
import jihye.backend_mock_exam.domain.exam.HistoryItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepository {

    private final HistoryMapper historyMapper;

    // 시험 히스토리 저장
    @Override
    public ExamHistory saveExamHistory(ExamHistory examHistory) {
        historyMapper.saveExamHistory(examHistory);
        return examHistory;
    }

    // 시험 히스토리 문항 저장
    @Override
    public HistoryItem saveExamHistoryItems(HistoryItem historyItem) {
        historyMapper.saveExamHistoryItems(historyItem);
        return historyItem;
    }

    // 히스토리 ID로 히스토리 조회
    @Override
    public ExamHistory findExamHistoryById(Long historyId) {
        return historyMapper.findExamHistoryById(historyId);
    }

    // 히스토리 ID로 히스토리 문항 조회
    @Override
    public List<Long> findQuestionsIdOfHistory(Long historyId, boolean isCorrect) {
        return historyMapper.findQuestionsIdOfHistory(historyId, isCorrect);
    }
}
