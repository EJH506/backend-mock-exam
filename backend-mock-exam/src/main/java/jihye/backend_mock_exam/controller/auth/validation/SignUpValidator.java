package jihye.backend_mock_exam.controller.auth.validation;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.auth.dto.SignUpDto;
import jihye.backend_mock_exam.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpValidator implements Validator {

    private static final String ACCOUNTID_PATTERN = "^[a-zA-z0-9_.]{4,30}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+={}\\[\\]:;\"'<>,.?/`~|-]).{8,20}$";

    private final AuthService authService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        SignUpDto dto = (SignUpDto) target;
        String accountId = dto.getAccountId();
        String password = dto.getPassword();
        String passwordCheck = dto.getPasswordCheck();

        // 아이디 정규표현식 검사
        if (!accountId.matches(ACCOUNTID_PATTERN)) {
            errors.rejectValue("accountId", "matches.user.accountId");
            return;
        }

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
