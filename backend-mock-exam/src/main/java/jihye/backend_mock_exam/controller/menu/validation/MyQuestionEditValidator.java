package jihye.backend_mock_exam.controller.menu.validation;

import jihye.backend_mock_exam.domain.exam.Answer;
import jihye.backend_mock_exam.service.menu.myQuestions.dto.MyQuestionAddDto;
import jihye.backend_mock_exam.service.menu.myQuestions.dto.MyQuestionEditDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MyQuestionEditValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MyQuestionEditDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        MyQuestionEditDto dto = (MyQuestionEditDto) target;
        String correct = dto.getCorrectAnswer().getAnswerText();
        String wrong1 = dto.getWrongAnswer1().getAnswerText();
        String wrong2 = dto.getWrongAnswer2().getAnswerText();
        String wrong3 = dto.getWrongAnswer3().getAnswerText();

        if (correct == null || correct.trim().isEmpty() ||
            wrong1 == null || wrong1.trim().isEmpty() ||
            wrong2 == null || wrong2.trim().isEmpty() ||
            wrong3 == null || wrong3.trim().isEmpty()) {
            errors.reject("required.answer");
        }

        /*
        if (wrongs != null) {
            for (String wrongAnswer : wrongs) {
                if (wrongAnswer == null || wrongAnswer.trim().isEmpty()) {
                    errors.reject("required.answer.wrong");
                    break;
                }
            }
        }
        */
    }
}
