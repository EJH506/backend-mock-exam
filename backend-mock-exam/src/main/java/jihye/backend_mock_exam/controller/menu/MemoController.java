package jihye.backend_mock_exam.controller.menu;

import jakarta.servlet.http.HttpServletRequest;
import jihye.backend_mock_exam.domain.memo.Memo;
import jihye.backend_mock_exam.domain.user.Guest;
import jihye.backend_mock_exam.domain.user.Role;
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

import java.util.List;

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
    public String memoWritePage(@RequestAttribute("user") Role user, Model model) {
        MemoWriteDto dto = new MemoWriteDto();
        dto.setUserId(user.getUserId());
        log.info("user.getUserId()={}",user.getUserId());

        model.addAttribute("user", user);
        model.addAttribute("memoWriteDto", dto);
        return "menu/memo/memo-write";
    }

    @PostMapping("/write")
    public String memoWrite(@ModelAttribute MemoWriteDto dto, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        Memo memo = memoService.saveMemo(dto, request);
        log.info("memo={}",memo);
        redirectAttributes.addAttribute("memoId", memo.getMemoId());
        return "redirect:/memo/{memoId}";
    }

    @GetMapping("/list")
    public String memoList(@RequestAttribute("user") Role user,
                           @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
                           @RequestParam(value = "page", defaultValue = "1") int page,
                           HttpServletRequest request,
                           Model model) {

        Page<Memo> memoList = memoService.memoList(user.getUserId(), request, searchKeyword, page);
        model.addAttribute("memoList", memoList);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("memoSelectDeleteDto", new MemoSelectDeleteDto());
        return "menu/memo/memo-list";
    }

    @GetMapping("/{memoId}")
    public String memoDetail(@RequestAttribute("user") Role user,
                             @PathVariable("memoId") Long memoId,
                             HttpServletRequest request,
                             Model model) {
        model.addAttribute("memo", memoService.memoDetail(memoId, user, request));
        model.addAttribute("memoSelectDeleteDto", new MemoSelectDeleteDto(List.of(memoId)));
        return "menu/memo/memo-detail";
    }

    @PostMapping("/delete")
    public String selectedMemoDelete(@RequestAttribute("user") Role user,
                                     @ModelAttribute MemoSelectDeleteDto dto,
                                     HttpServletRequest request) {

        memoService.memoSelectDelete(dto, user, request);
        return "redirect:/memo/list";
    }

    @GetMapping("/{memoId}/edit")
    public String memoEditPage(@RequestAttribute("user") Role user,
                               @PathVariable("memoId") Long memoId,
                               HttpServletRequest request,
                               Model model) {

        Memo memo = memoService.memoDetail(memoId, user, request);
        model.addAttribute("memoEditDto", new MemoEditDto(memoId, memo.getMemoText()));
        return "menu/memo/memo-edit";
    }

    @PostMapping("/{memoId}/edit")
    public String memoEdit(@RequestAttribute("user") Role user,
                           @ModelAttribute MemoEditDto dto,
                           HttpServletRequest request,
                           RedirectAttributes redirectAttributes) {
        memoService.editMemo(dto, user, request);
        redirectAttributes.addAttribute("memoId", dto.getMemoId());
        return "redirect:/memo/{memoId}";
    }
}
