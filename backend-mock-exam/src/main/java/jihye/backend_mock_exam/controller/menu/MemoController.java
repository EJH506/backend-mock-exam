package jihye.backend_mock_exam.controller.menu;

import jihye.backend_mock_exam.domain.memo.Memo;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.Page;
import jihye.backend_mock_exam.service.menu.memo.MemoService;
import jihye.backend_mock_exam.service.menu.memo.dto.MemoEditDto;
import jihye.backend_mock_exam.service.menu.memo.dto.MemoSelectDeleteDto;
import jihye.backend_mock_exam.service.menu.memo.dto.MemoWriteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String memoWritePage(@RequestAttribute("user") User user, Model model) {
        MemoWriteDto dto = new MemoWriteDto();
        dto.setUserId(user.getUserId());

        model.addAttribute("user", user);
        model.addAttribute("memoWriteDto", dto);
        return "menu/memo/memo-write";
    }

    @PostMapping("/write")
    public String memoWrite(@ModelAttribute MemoWriteDto dto, RedirectAttributes redirectAttributes) {

        Memo memo = memoService.saveMemo(dto);
        log.info("memo={}",memo);
        redirectAttributes.addAttribute("memoId", memo.getMemoId());
        return "redirect:/memo/{memoId}";
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

    @GetMapping("/{memoId}/edit")
    public String memoEditPage(@PathVariable("memoId") Long memoId, Model model) {

        Memo memo = memoService.memoDetail(memoId);
        model.addAttribute("memoEditDto", new MemoEditDto(memoId, memo.getMemoText()));
        return "menu/memo/memo-edit";
    }

    @PostMapping("/{memoId}/edit")
    public String memoEdit(@ModelAttribute MemoEditDto dto, RedirectAttributes redirectAttributes) {
        memoService.editMemo(dto);
        redirectAttributes.addAttribute("memoId", dto.getMemoId());
        return "redirect:/memo/{memoId}";
    }
}
