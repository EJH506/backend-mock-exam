package jihye.backend_mock_exam.controller;

import jihye.backend_mock_exam.domain.user.Guest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

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

    @GetMapping("/")
    public String home(@SessionAttribute(value = "guest", required = false) Guest guest, Model model) {

        // 스프링 시큐리티 인증정보
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication={}", authentication);

        // 회원일 시
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails loginUser = (UserDetails) authentication.getPrincipal();
            model.addAttribute("user", loginUser);
            log.info("user={}", loginUser);
            // 회원이 아닐 시
        } else {

            // 게스트 권한도 없을 시
            if (guest == null) {
                return "welcome";
            }
            
            // 게스트 일 시
            model.addAttribute("user", guest);
            log.info("guest={}", guest);
        }

        return "home";
    }
}
