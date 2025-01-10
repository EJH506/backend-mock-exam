package jihye.backend_mock_exam.controller;

import jihye.backend_mock_exam.domain.user.Guest;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class Menus {

    public Map<String, Object> menus(@SessionAttribute(value = "guest", required = false) Guest guest) {

        boolean isMember = false;
        if (guest == null) {
            isMember = true;
        }

        Map<String, Object> menus = new LinkedHashMap<>();
        menus.put("exam",  Map.of("name", "문제풀기", "able", true));
        menus.put("incorrectNote", Map.of("name", "오답노트", "able", isMember));
        menus.put("history", Map.of("name", "히스토리", "able", isMember));
        menus.put("memo", Map.of("name", "메모장", "able", true));
        menus.put("myQuestions", Map.of("name", "나만의 문제 등록", "able", isMember));

        return menus;
    }

}
