package jihye.backend_mock_exam.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public String handleGeneralException(Exception e, Model model) {
//        model.addAttribute("errorMessage", "오류가 발생했습니다.");
//        return "auth/signup";
//    }
}
