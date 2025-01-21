package jihye.backend_mock_exam.repository.menu.history;

import jihye.backend_mock_exam.domain.history.ExamHistory;
import jihye.backend_mock_exam.domain.history.HistoryItem;

import java.util.List;

public interface HistoryRepository {

    // 시험 히스토리 저장
    ExamHistory saveExamHistory(ExamHistory examHistory);

    // 시험 히스토리 문항 저장
    HistoryItem saveExamHistoryItems(HistoryItem historyItem);

    // 히스토리 목록 조회 (페이징)
    List<ExamHistory> findExamHistoryByUser(Long userId, int offset, int pageSize);

    // 히스토리 목록 총 갯수
    int findTotalExamHistoryCountByUser(Long userId);

    // 히스토리 ID로 히스토리 조회
    ExamHistory findExamHistoryById(Long historyId);

    // 히스토리 ID로 히스토리 문항 조회 (전체를 찾으려면 true, 틀린문제만이면 false)
    List<Long> findQuestionsIdOfHistory(Long historyId, boolean isCorrect);

    // 히스토리 ID로 히스토리 아이템 조회
    List<HistoryItem> findHistoryItemById(Long historyId, boolean isCorrect);
}
