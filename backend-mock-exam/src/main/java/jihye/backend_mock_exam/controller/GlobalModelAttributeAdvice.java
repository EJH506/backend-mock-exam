package jihye.backend_mock_exam.controller;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributeAdvice {

    private final Menus menus;
    private final IsMember isMember;
    private final IsAdmin isAdmin;
    private final AuthService authService;

    @ModelAttribute("menus")
    public Map<String, Object> menus() {
        return menus.menus();
    }

    @ModelAttribute("isMember")
    public boolean isMember() {
        return isMember.isMember();
    }

    @ModelAttribute("hasAuthority")
    public boolean hasAuthority() {
        return isMember() || isAdmin.isAdmin();
    }

}
