package jihye.backend_mock_exam.controller.menu.validation;

import jihye.backend_mock_exam.service.menu.myQuestions.dto.MyQuestionAddDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class MyQuestionValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MyQuestionAddDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        MyQuestionAddDto dto = (MyQuestionAddDto) target;
        String correct = dto.getCorrectAnswer();
        List<String> wrongs = dto.getWrongAnswers();

        if (correct == null || correct.trim().isEmpty()) {
            errors.reject("required.answer.correct");
        }

        if (wrongs != null) {
            for (String wrongAnswer : wrongs) {
                if (wrongAnswer == null || wrongAnswer.trim().isEmpty()) {
                    errors.reject("required.answer.wrong");
                    break;
                }
            }
        }
    }
}
