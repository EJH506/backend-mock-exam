package jihye.backend_mock_exam.service.menu.history;

import jihye.backend_mock_exam.domain.Page;
import jihye.backend_mock_exam.domain.history.ExamHistory;
import jihye.backend_mock_exam.domain.history.HistoryItemObject;
import jihye.backend_mock_exam.repository.menu.history.HistoryRepository;
import jihye.backend_mock_exam.service.menu.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final CommonService commonService;
    // 히스토리 목록 조회
    public Page<ExamHistory> findExamHistoryByUser(Long userId, int page) {

        int pagePerItem = 5;                   // 페이지당 아이템 수
        int blockPerPage = 5;                  // 블럭당 페이지 수
        int offset = (page - 1) * pagePerItem; // limit 시작위치

        List<ExamHistory> histories = historyRepository.findExamHistoryByUser(userId, offset, pagePerItem);

        int totalCount = historyRepository.findTotalExamHistoryCountByUser(userId);
        int totalPages = (int) Math.ceil((double) totalCount / pagePerItem);
        int currentBlock = (int) Math.floor((double) (page - 1) / blockPerPage);

        return new Page<>(histories, page, totalPages, currentBlock, totalCount);
    }

    // 히스토리 조회
    public ExamHistory findExamHistoryById(Long historyId) {
        return historyRepository.findExamHistoryById(historyId);
    }

    // 히스토리 상세 반환
    public List<HistoryItemObject> createHistoryDetails(ExamHistory examHistory, String option) {
        return commonService.createHistoryDetails(examHistory, option);
    }

    // 히스토리에 속한 문제ID 조회
    public List<Long> findQuestionsIdOfHistory(Long historyId, boolean isCorrect) {
        return historyRepository.findQuestionsIdOfHistory(historyId, isCorrect);
    }

}
