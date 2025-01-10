package jihye.backend_mock_exam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributeAdvice {

    private final Menus menus;
    private final IsMember isMember;
    private final IsAdmin isAdmin;

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
