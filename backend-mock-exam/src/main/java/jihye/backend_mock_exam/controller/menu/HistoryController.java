package jihye.backend_mock_exam.controller.menu;

import jihye.backend_mock_exam.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @GetMapping
    public String historyList(@RequestAttribute("user") User user) {



        return "menu/history/history-list";
    }
    
    @GetMapping("/{historyId}")
    public String historyDetail() {


        return "menu/history/history-detail";
    }
}
