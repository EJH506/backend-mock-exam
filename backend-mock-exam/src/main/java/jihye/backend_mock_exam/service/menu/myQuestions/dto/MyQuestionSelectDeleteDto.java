package jihye.backend_mock_exam.service.menu.myQuestions.dto;

import lombok.Data;

import java.util.List;

@Data
public class MyQuestionSelectDeleteDto {

    private List<Long> deleteQuestionsId;

    public MyQuestionSelectDeleteDto() {
    }

    public MyQuestionSelectDeleteDto(List<Long> deleteQuestionsId) {
        this.deleteQuestionsId = deleteQuestionsId;
    }
}
