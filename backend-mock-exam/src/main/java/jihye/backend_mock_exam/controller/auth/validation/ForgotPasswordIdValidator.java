package jihye.backend_mock_exam.controller.auth.validation;

import jihye.backend_mock_exam.service.auth.AuthService;
import jihye.backend_mock_exam.service.auth.dto.ForgotPasswordIdDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ForgotPasswordIdValidator implements Validator {

    private static final String ACCOUNTID_PATTERN = "^[a-zA-z0-9_.]{4,30}$";

    private final AuthService authService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ForgotPasswordIdDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ForgotPasswordIdDto dto = (ForgotPasswordIdDto) target;

        // 아이디 존재 유무 검사
        boolean isExists = authService.validateDuplicateId(dto.getAccountId());
        if (!isExists) {
            errors.rejectValue("accountId", "non-exists.user.accountId");
            return;
        }

        // 아이디 정규표현식 검사
        if (!dto.getAccountId().matches(ACCOUNTID_PATTERN)) {
            errors.rejectValue("accountId", "matches.user.accountId");
            return;
        }
    }
}
