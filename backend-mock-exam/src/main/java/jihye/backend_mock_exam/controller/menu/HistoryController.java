package jihye.backend_mock_exam.controller.menu;

import jihye.backend_mock_exam.domain.history.ExamHistory;
import jihye.backend_mock_exam.domain.history.HistoryItemObject;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.menu.history.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    public String historyList(@RequestAttribute("user") User user, Model model) {
        List<ExamHistory> histories = historyService.findExamHistoryByUser(user.getUserId());
        model.addAttribute("histories", histories);

        return "menu/history/history-list";
    }
    
    @GetMapping("/{historyId}")
    public String historyDetail(@PathVariable("historyId") Long historyId,
                                @RequestParam(value = "option", defaultValue = "all") String option,
                                Model model) {
        ExamHistory examHistory = historyService.findExamHistoryById(historyId);
        List<Long> questionsIdOfHistory = historyService.findQuestionsIdOfHistory(historyId, false);


        List<HistoryItemObject> historyDetails = historyService.createHistoryDetails(examHistory, option);

        model.addAttribute("examHistory", examHistory);
        model.addAttribute("historyDetails", historyDetails);

        log.info("examHistory={}", examHistory);
        log.info("historyDetails={}", historyDetails);

        return "menu/history/history-detail";
    }
}
