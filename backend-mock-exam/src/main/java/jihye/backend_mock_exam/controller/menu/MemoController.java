package jihye.backend_mock_exam.controller.menu;

import jihye.backend_mock_exam.domain.memo.Memo;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.Page;
import jihye.backend_mock_exam.service.menu.memo.MemoService;
import jihye.backend_mock_exam.service.menu.memo.dto.MemoSelectDeleteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping
    public String memo() {
        return "redirect:/memo/list";
    }

    @GetMapping("/write")
    public String memoWrite() {
        return "menu/memo/memo-write";
    }

    @GetMapping("/list")
    public String memoList(@RequestAttribute("user") User user,
                           @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
                           @RequestParam(value = "page", defaultValue = "1") int page,
                           Model model) {

        Page<Memo> memoList = memoService.memoList(user.getUserId(), searchKeyword, page);
        model.addAttribute("memoList", memoList);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("memoSelectDeleteDto", new MemoSelectDeleteDto());
        return "menu/memo/memo-list";
    }

    @GetMapping("/{memoId}")
    public String memoDetail(@PathVariable("memoId") Long memoId, Model model) {
        model.addAttribute("memo", memoService.memoDetail(memoId));
        return "menu/memo/memo-detail";
    }

    @PostMapping("/delete")
    public String selectedMemoDelete(@ModelAttribute MemoSelectDeleteDto dto) {

        memoService.memoSelectDelete(dto);

        log.info("MemoSelectDeleteDto={}", dto);
        return "redirect:/memo/list";
    }
}
