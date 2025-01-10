package jihye.backend_mock_exam.controller;

import jihye.backend_mock_exam.domain.user.Guest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class Menus {

    private final IsMember isMemberUtils;
    private final IsAdmin isAdminUtils;

    public Map<String, Object> menus() {

        boolean isMember = isMemberUtils.isMember();
        boolean isAdmin = isAdminUtils.isAdmin();
        boolean hasAuthority = isMember || isAdmin;

        Map<String, Object> menus = new LinkedHashMap<>();
        menus.put("exam",  Map.of("name", "문제풀기", "able", true));
        menus.put("incorrect-note", Map.of("name", "오답노트", "able", hasAuthority));
        menus.put("history", Map.of("name", "히스토리", "able", hasAuthority));
        menus.put("memo", Map.of("name", "메모장", "able", true));
        menus.put("my-questions", Map.of("name", "나만의 문제 등록", "able", hasAuthority));

        if (isAdmin) {
            menus.put("admin", Map.of("name", "관리하기", "able", true));
        }
        return menus;
    }

}
