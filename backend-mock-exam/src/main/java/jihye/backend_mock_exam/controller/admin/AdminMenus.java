package jihye.backend_mock_exam.controller.admin;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class AdminMenus {

    public Map<String, String> adminMenus() {

        Map<String, String> menus = new LinkedHashMap<>();
        menus.put("exam", "문제풀기");
        menus.put("subject", "문제관리");
        menus.put("users", "회원관리");
        menus.put("exit", "관리 마치기");

        return menus;
    }
}
