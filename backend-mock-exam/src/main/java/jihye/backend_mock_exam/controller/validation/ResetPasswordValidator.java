package jihye.backend_mock_exam.controller.validation;

import jihye.backend_mock_exam.service.auth.dto.ResetPasswordDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ResetPasswordValidator implements Validator {

    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+={}\\[\\]:;\"'<>,.?/`~|-]).{8,20}$";

    @Override
    public boolean supports(Class<?> clazz) {
        return ResetPasswordDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ResetPasswordDto dto = (ResetPasswordDto)target;
        String password = dto.getPassword();
        String passwordCheck = dto.getPasswordCheck();

        // 비밀번호 정규표현식 검사
        if (!password.matches(PASSWORD_PATTERN)) {
            errors.rejectValue("password", "matches.user.password");
            return;
        }

        // 비밀번호 확인 검사
        if (!password.equals(passwordCheck)) {
            errors.rejectValue("passwordCheck", "unmatched.user.passwordCheck");
            return;
        }
    }
}
