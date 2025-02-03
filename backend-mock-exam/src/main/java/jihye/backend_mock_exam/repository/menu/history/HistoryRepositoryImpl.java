package jihye.backend_mock_exam.repository.menu.history;

import jihye.backend_mock_exam.domain.Questions;
import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.domain.history.ExamHistory;
import jihye.backend_mock_exam.domain.history.HistoryItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    // 히스토리 목록 조회 (페이징)
    @Override
    public List<ExamHistory> findExamHistoryByUser(Long userId, int offset, int pageSize) {
        return historyMapper.findExamHistoryByUser(userId, offset, pageSize);
    }

    // 히스토리 목록 총 갯수
    @Override
    public int findTotalExamHistoryCountByUser(Long userId) {
        return historyMapper.findTotalExamHistoryCountByUser(userId);
    }

    // 히스토리 ID로 히스토리 조회
    @Override
    public ExamHistory findExamHistoryById(Long historyId) {
        return historyMapper.findExamHistoryById(historyId);
    }

    // 히스토리 ID로 히스토리 문항 조회 (전체를 찾으려면 true, 틀린문제만이면 false)
    @Override
    public List<Questions> findQuestionsIdOfHistory(Long historyId, boolean isCorrect) {
        return historyMapper.findQuestionsIdOfHistory(historyId, isCorrect);
    }

    // 히스토리 ID로 히스토리 아이템 조회
    @Override
    public List<HistoryItem> findHistoryItemById(Long historyId, boolean isCorrect) {
        return historyMapper.findHistoryItemById(historyId, isCorrect);
    }
}
