package jihye.backend_mock_exam.repository.menu.history;

import jihye.backend_mock_exam.domain.exam.ExamHistory;
import jihye.backend_mock_exam.domain.exam.HistoryItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryMapper {

    // 시험 히스토리 저장
    void saveExamHistory(ExamHistory examHistory);

    // 시험 히스토리 문항 저장
    void saveExamHistoryItems(HistoryItem historyItem);

    // 히스토리 ID로 히스토리 조회
    ExamHistory findExamHistoryById(Long historyId);

    // 히스토리 ID로 히스토리 문항 조회
    List<Long> findQuestionsIdOfHistory(@Param("historyId") Long historyId, @Param("isCorrect") boolean isCorrect);

}
