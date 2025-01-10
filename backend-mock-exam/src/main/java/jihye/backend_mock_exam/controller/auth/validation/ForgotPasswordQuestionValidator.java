package jihye.backend_mock_exam.controller.auth.validation;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.auth.AuthService;
import jihye.backend_mock_exam.service.auth.dto.ForgotPasswordIdDto;
import jihye.backend_mock_exam.service.auth.dto.ForgotPasswordQuestionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
@RequiredArgsConstructor
public class ForgotPasswordQuestionValidator implements Validator {

    private final AuthService authService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ForgotPasswordQuestionDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ForgotPasswordQuestionDto dto = (ForgotPasswordQuestionDto)target;

        User findUser = authService.findPasswordId(dto.getAccountId());
        String DBQuestion = findUser.getFindPasswordQuestion();
        String DBAnswer = findUser.getFindPasswordAnswer();

        String inputQuestion = dto.getFindPasswordQuestion();
        String inputAnswer = dto.getFindPasswordAnswer();

        if (!DBQuestion.equals(inputQuestion) || !DBAnswer.equals(inputAnswer)) {
            errors.rejectValue("findPasswordAnswer", "unmatched");
            return;
        }
    }
}
