package jihye.backend_mock_exam.repository.menu.history;

import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.domain.history.ExamHistory;
import jihye.backend_mock_exam.domain.history.HistoryItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryMapper {

    // 시험 히스토리 저장
    void saveExamHistory(ExamHistory examHistory);

    // 시험 히스토리 문항 저장
    void saveExamHistoryItems(HistoryItem historyItem);

    // 히스토리 목록 조회
    List<ExamHistory> findExamHistoryByUser(Long userId);

    // 히스토리 ID로 히스토리 조회
    ExamHistory findExamHistoryById(Long historyId);

    // 히스토리 ID로 히스토리 문항 조회
    List<Question> findQuestionsOfHistory(@Param("historyId") Long historyId, @Param("isCorrect") boolean isCorrect);

}
