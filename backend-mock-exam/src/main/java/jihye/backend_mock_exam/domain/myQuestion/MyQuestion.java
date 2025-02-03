package jihye.backend_mock_exam.domain.myQuestion;

import jihye.backend_mock_exam.controller.menu.ExamConst;
import jihye.backend_mock_exam.domain.Questions;
import lombok.Data;

@Data
public class MyQuestion implements Questions {

    private Long questionId;
    private Long subjectId; // take-exam 템플릿 때문에 임시
    private String subjectName;
    private Long userId;
    private int level;
    private String questionText;
    private boolean deleted;
    private String createdAt;
    private String updateAt;

    public MyQuestion() {
        this.subjectId = 999L; //임시
        this.subjectName = ExamConst.SUBJECT_MYQUESTIONS;
    }

    public MyQuestion(Long questionId, Long userId, int level, String questionText) {
        this.questionId = questionId;
        this.subjectId = 999L; //임시
        this.subjectName = ExamConst.SUBJECT_MYQUESTIONS;
        this.userId = userId;
        this.level = level;
        this.questionText = questionText;
    }
}
