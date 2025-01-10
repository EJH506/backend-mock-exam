package jihye.backend_mock_exam.controller.users;

import jakarta.validation.Valid;
import jihye.backend_mock_exam.controller.IsMember;
import jihye.backend_mock_exam.controller.Menus;
import jihye.backend_mock_exam.domain.user.Guest;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.users.UsersService;
import jihye.backend_mock_exam.service.users.dto.EditAccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final Menus menus;
    private final IsMember isMember;

    @ModelAttribute("menus")
    public Map<String, Object> menus(@SessionAttribute(value = "guest", required = false) Guest guest) {
        return menus.menus(guest);
    }

    @ModelAttribute("isMember")
    public boolean isMember() {
        return isMember.isMember();
    }

    @ModelAttribute("user")
    public User loginUser(@PathVariable("userId") long userId) {
        return usersService.userInfo(userId).orElseThrow();
    }

    @GetMapping("/edit/{userId}")
    public String editAccountPage(@PathVariable("userId") long userId, @SessionAttribute(value = "guest", required = false) Guest guest, Model model) {
        User user = loginUser(userId);

        EditAccountDto dto = new EditAccountDto(user.getUserId(), user.getAccountId(), user.getNickname(), user.getGender(), user.getBirthYear());

        model.addAttribute("editAccountDto", dto);
        return "users/edit-account";
    }

    @PostMapping("/edit/{userId}")
    public String editAccount(@PathVariable("userId") long userId,
                              @Valid @ModelAttribute EditAccountDto dto, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "users/edit-account";
        }

        boolean updateResult = usersService.editAccount(userId, dto);

        if (!updateResult) {
            log.info("updateResult == false");
            bindingResult.rejectValue("password", "unmatched.user.password");
            return "users/edit-account";
        }

        redirectAttributes.addAttribute("userId", userId);
        redirectAttributes.addFlashAttribute("status", "edit-success");

        return "redirect:/users/edit/{userId}";
    }

    @GetMapping("/delete/{userId}")
    public String deleteAccount() {
        return "users/delete-account";
    }

}
