package jihye.backend_mock_exam.service.menu.history;

import jihye.backend_mock_exam.domain.Questions;
import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.domain.history.HistoryItem;
import jihye.backend_mock_exam.service.Page;
import jihye.backend_mock_exam.domain.history.ExamHistory;
import jihye.backend_mock_exam.domain.history.HistoryItemObject;
import jihye.backend_mock_exam.repository.menu.history.HistoryRepository;
import jihye.backend_mock_exam.service.menu.CommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final CommonService commonService;
    // 히스토리 목록 조회
    public Page<ExamHistory> findExamHistoryByUser(Long userId, int page) {

        int pagePerItem = 5;                   // 페이지당 아이템 수
        int blockPerPage = 5;                  // 블럭당 페이지 수
        int offset = (page - 1) * pagePerItem;

        List<ExamHistory> histories = historyRepository.findExamHistoryByUser(userId, offset, pagePerItem);

        int totalCount = historyRepository.findTotalExamHistoryCountByUser(userId);

        return new Page<>(histories, page, totalCount, pagePerItem, blockPerPage);
    }

    // 히스토리 조회
    public ExamHistory findExamHistoryById(Long historyId) {

        ExamHistory examHistory = historyRepository.findExamHistoryById(historyId);
        List<HistoryItem> historyItems;

        // examHistory에 질문, 답변 리스트 데이터가 없으면 조회해서 담음
        if (examHistory.getQuestions() == null || examHistory.getIsMyQuestions() == null || examHistory.getCorrectAnswers() == null || examHistory.getUserAnswers() == null) {
            historyItems = historyRepository.findHistoryItemById(examHistory.getHistoryId(), true);
            List<Long> historyQuestionsId = new ArrayList<>();
            List<Boolean> historyIsMyQuestions = new ArrayList<>();
            List<Long> historyCorrectAnswersId = new ArrayList<>();
            List<Long> historyUserAnswersId = new ArrayList<>();

            for (HistoryItem historyItem : historyItems) {
                historyQuestionsId.add(historyItem.getQuestionId());
                historyIsMyQuestions.add(historyItem.isMyQuestion());
                historyCorrectAnswersId.add(historyItem.getCorrectAnswerId());
                historyUserAnswersId.add(historyItem.getUserAnswerId());
                log.info("historyItem={}", historyItem);
            }
            examHistory.setQuestions(historyQuestionsId);
            examHistory.setIsMyQuestions(historyIsMyQuestions);
            examHistory.setCorrectAnswers(historyCorrectAnswersId);
            examHistory.setUserAnswers(historyUserAnswersId);
        }

        return examHistory;
    }

    // 히스토리 상세 반환
    public List<HistoryItemObject> createHistoryDetails(ExamHistory examHistory, String option) {
        return commonService.createHistoryDetails(examHistory, option);
    }

    // 히스토리에 속한 문제ID 조회
    public List<Questions> findQuestionsIdOfHistory(Long historyId, boolean isCorrect) {
        return historyRepository.findQuestionsIdOfHistory(historyId, isCorrect);
    }

}
