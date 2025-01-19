package jihye.backend_mock_exam.repository.menu.history;

import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.domain.history.ExamHistory;
import jihye.backend_mock_exam.domain.history.HistoryItem;

import java.util.List;

public interface HistoryRepository {

    // 시험 히스토리 저장
    ExamHistory saveExamHistory(ExamHistory examHistory);

    // 시험 히스토리 문항 저장
    HistoryItem saveExamHistoryItems(HistoryItem historyItem);

    // 히스토리 목록 조회
    List<ExamHistory> findExamHistoryByUser(Long userId);

    // 히스토리 ID로 히스토리 조회
    ExamHistory findExamHistoryById(Long historyId);

    // 히스토리 ID로 히스토리 문항 조회
    List<Question> findQuestionsOfHistory(Long historyId, boolean isCorrect);

}
