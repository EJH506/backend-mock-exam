package jihye.backend_mock_exam.controller.menu.validation;

import jihye.backend_mock_exam.service.menu.myQuestions.dto.MyQuestionAddDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
        String wrong1 = dto.getWrongAnswer1();
        String wrong2 = dto.getWrongAnswer2();
        String wrong3 = dto.getWrongAnswer3();

        if (correct == null || correct.trim().isEmpty() ||
            wrong1 == null || wrong1.trim().isEmpty() ||
            wrong2 == null || wrong2.trim().isEmpty() ||
            wrong3 == null || wrong3.trim().isEmpty()
            ) {
            errors.rejectValue("correctAnswer", "");
        }


    }
}
