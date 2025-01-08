package jihye.backend_mock_exam.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @GetMapping
    public String historyList() {
        return "menu/history/history-list";
    }
    
    @GetMapping("/{historyId}")
    public String historyDetail() {
        return "menu/history/history-detail";
    }
}
